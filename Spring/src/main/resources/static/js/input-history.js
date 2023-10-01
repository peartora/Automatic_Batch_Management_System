window.onload = function()
{
    createAjaxToUpdateOptionsForSelectTag();
    createAjaxToupdateInputHistory();
}

function createAjaxToUpdateOptionsForSelectTag()
{
    let httpRequest = new XMLHttpRequest();
    httpRequest.onreadystatechange = callback;
    httpRequest.open("GET", "/batchInformation/update-options", true);
    httpRequest.setRequestHeader('Content-Type', 'application/json')
    httpRequest.send();

    function callback() {
        if(httpRequest.readyState === 4) {
            if(httpRequest.status === 200) {
                let json = httpRequest.responseText;
                const datas = JSON.parse(json)

                console.log(datas);

                for(let i = 0; i < datas.length; i++)
                {
                    let partName = datas[i]["partName"];
                    updateOptionsForSelectTag(partName);
                }
            }
        }
    }
}

function updateOptionsForSelectTag(partName)
{
    let selectElement = document.getElementById("part-name");
    let optionElement = document.createElement("option");
    optionElement.innerText = partName;
    selectElement.append(optionElement);
}

// document onload 되고, "part-name"의 요소에 변경이 있을 경우 Ajax 통신 시작.
function createAjaxToupdateInputHistory()
{
    document.getElementById("part-name").onchange = function(e)
    {
        e.preventDefault()

        let httpRequest = new XMLHttpRequest();
        httpRequest.onreadystatechange = callback;
        httpRequest.open("GET", "/batchInformation/", true);
        httpRequest.setRequestHeader('Content-Type', 'application/json')
        httpRequest.send();

        function callback() {
            if(httpRequest.readyState === 4) {
                if(httpRequest.status === 200) {
                    let json = httpRequest.responseText;
                    const datas = JSON.parse(json)

                    console.log(datas);

                    for(let i = 0; i < datas.length; i++)
                    {
                        let partName = datas[i]["partName"];
                        createTableForInputHistory();
                    }
                }
            }
        }
    }
}

function createTableForInputHistory()
{
    return "";
}





















