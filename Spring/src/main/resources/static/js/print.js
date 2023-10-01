let printButton = document.getElementById("printButton");
let printContext = dpcument.getElementById("printContext");

function printPage(datas) {
    const COUNT = datas.printTimes;
    const CONTEXT = `
        <div class="page">내용...</div>
    `;

    printContext.innerHTML = `${CONTEXT.repeat(COUNT)}`;

    print();
}

printButton.addEventListener("click", print);