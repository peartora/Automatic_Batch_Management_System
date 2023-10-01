window.onload = function() {
    document.getElementById("barcode").focus();

    document.getElementById("my-form").onsubmit = function(e) {
        e.preventDefault()

        let context = document.getElementById("barcode").value;
        let splitedContext = context.split(',');
        let material = context.split(',')[0];
        let splittedMaterial = material.split(".")
        let batchNumber = context.split(',')[1];

        if(splitedContext.length == 2)
        {
            if(splittedMaterial.length == 3)
            {
                material = material.toUpperCase();

                let dataSet = {
                    "material" : material,
                    "batchNumber" : batchNumber
                }

                const httpRequest = new XMLHttpRequest();
                httpRequest.onreadystatechange = callback;
                httpRequest.open("POST", "/batchInformation/handling-service", true);
                httpRequest.setRequestHeader('Content-Type', 'application/json');
                httpRequest.send(JSON.stringify(dataSet));

                function callback() {
                    if(httpRequest.readyState === 4)
                    {
                        if(httpRequest.status === 200)
                        {
                            const json = httpRequest.responseText;
                            const datas = JSON.parse(json)

                            createPrintContext(datas);
                        }
                        else if (httpRequest.status >= 400)
                        {
                            const json = httpRequest.responseText;
                            const data = JSON.parse(json);
                            if (data.error)
                            {
                                alert(data.message);
                            }
                            else
                            {
                                alert('통신 도중 할 수 없는 에러가 발생했습니다.');
                            }
                        }
                    }
                    document.getElementById("barcode").focus();
                }
            }
            else
            {
                alert("지정 된 부품 번호의 양식이 아닙니다. 다시 확인 바랍니다.");

            }
        }
        else
        {
            alert("지정 된 슬립의 양식이 아닙니다. 다시 확인 바랍니다.");
        }
        document.getElementById("barcode").value = null;
    }
}

function createPrintContext(datas)
{

    const printable = document.querySelector("#printable");
    printable.innerHTML = '';

    if(datas.batchUpdated === false)
    {
        document.getElementById("reading-result").innerHTML = "동일 배치 이므로 출력 내용 없습니다.";
    }
    else
    {
        document.getElementById("reading-result").innerHTML = "새로운 배치 입니다. 아래 내용을 출력 합니다.";

        const pageTemplate = document.querySelector('#page-template');
        for (let i = 0; i < datas.printTimes; i++)
        {
            const page = document.importNode(pageTemplate.content, true);
            page.querySelector(".part-name").textContent = `부품이름 : ${datas.partName}`;
            page.querySelector(".part-number").textContent = `부품번호 : ${datas.partNumber}`;
            page.querySelector(".barcode-number").textContent = `배치번호 : ${datas.batchNumber}`;
            page.querySelector(".time").textContent = `투입시간 : ${dayjs(datas.now).format('YYYY-MM-DD HH:mm:ss')}`;
            JsBarcode(page.querySelector('.barcode-image'), datas.batchNumber);

            printable.appendChild(page);
        }
        print();
    }
}