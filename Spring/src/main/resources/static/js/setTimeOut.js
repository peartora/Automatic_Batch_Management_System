window.onload = function() {

    const intervalId = setInterval(loadDate, 3000);
    //clearInterval(intervalId);
}

function loadDate()
{
    let httpRequest = new XMLHttpRequest();
    httpRequest.onreadystatechange = callback;
    httpRequest.open("GET", "/batchInformation/display-service", true);
    httpRequest.setRequestHeader('Content-Type', 'application/json')
    httpRequest.send();

    function callback() {
        if (httpRequest.readyState === 4) {
            if (httpRequest.status === 200) {
                let json = httpRequest.responseText;
                const datas = JSON.parse(json)

                console.log(datas);

                for(let i = 0; i < datas.length; i++)
                {
                    let partName = datas[i]["partName"];
                    let batchNumber = datas[i]["batchNumber"];
                    let currentTime = datas[i]["now"];

                    updateDOM(partName, batchNumber, currentTime);
                }
            }
        }
    }
}

// 아래 node 처리가 잘 안 됨,(text 노드 때문?)
// 아래 코드를 (DOM)개선 할 수 없을지?
// 시간을 조금 더 읽기 쉽게 변경 가능 할지?
function updateDOM(partName, batchNumber, currentTime)
{
    let element = document.getElementById(partName);

    let timeNode = element.lastChild.previousSibling;
    timeNode.innerHTML = currentTime;

    let batchNode = element.lastChild.previousSibling.previousSibling.previousSibling;
    let currentBatchNumber = batchNode.innerHTML;
    batchNode.innerHTML = batchNumber;

    if(currentBatchNumber !== batchNumber)
    {
        element.style.backgroundColor = "skyblue";
    }
    else
    {
        element.style.backgroundColor = "white";
    }

}