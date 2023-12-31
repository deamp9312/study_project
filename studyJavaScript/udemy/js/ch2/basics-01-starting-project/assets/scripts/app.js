let defaultResult = 0 ;
let currentResult = defaultResult;
let calculationDescription = `hihi ${currentResult}`;
let logEntries =[];

const getUserNumberInput = () =>{
    return parseInt(userInput.value);
}

const createAndWriteOutput = (operator,resultBeforeCalc,calcNumber) =>{
    const calcDescription = `${resultBeforeCalc} ${operator} ${calcNumber}`;
    outputResult(currentResult,calcDescription);
}

function add(){
    const enteredNumber = getUserNumberInput();
    const initialResult = currentResult;
    currentResult += enteredNumber;
    createAndWriteOutput('+',initialResult,enteredNumber);
    logEntries.push(enteredNumber);
    console.log(logEntries);
}
const subtract = () =>{
    const enteredNumber = getUserNumberInput();
    const initialResult = currentResult;
    currentResult -= enteredNumber;
    createAndWriteOutput('-',initialResult,enteredNumber);
}

const multiply = () =>{
    const enteredNumber = getUserNumberInput();
    const initialResult = currentResult;
    currentResult *= enteredNumber;
    createAndWriteOutput('*',initialResult,enteredNumber);
}

const divide = () =>{
    const enteredNumber = getUserNumberInput();
    const initialResult = currentResult;
    currentResult /=  enteredNumber;
    createAndWriteOutput('/',initialResult,enteredNumber);
}
// currentResult = currentResult + 10;
let errorMessage = 'An error \n' + 'occurred!';
//wite-space : pre 로 설정하면 줄바꿈을 화면상에서 볼수있음.


addBtn.addEventListener('click',add);
subtractBtn.addEventListener('click',subtract);
multiplyBtn.addEventListener('click',multiply);
divideBtn.addEventListener('click',divide);
