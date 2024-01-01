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

const writeToLog = (operationIdentifier,prevResult,operationNumber,newResult) =>{
    const logEntry = {
        operation : operationIdentifier,
        prevResult  : prevResult,
        number : operationNumber,
        result : newResult
    }
    console.log(logEntry);
    logEntries.push(operationNumber);
    console.log(logEntries);

}

function add(){
    const enteredNumber = getUserNumberInput();
    const initialResult = currentResult;
    currentResult += enteredNumber;
    createAndWriteOutput('+',initialResult,enteredNumber);
    writeToLog('ADD',initialResult,enteredNumber,currentResult);
    
}
const subtract = () =>{
    const enteredNumber = getUserNumberInput();
    const initialResult = currentResult;
    currentResult -= enteredNumber;
    createAndWriteOutput('-',initialResult,enteredNumber);
    writeToLog('SUBTRACT',initialResult,enteredNumber,currentResult);
}

const multiply = () =>{
    const enteredNumber = getUserNumberInput();
    const initialResult = currentResult;
    currentResult *= enteredNumber;
    createAndWriteOutput('*',initialResult,enteredNumber);
    writeToLog('MLTIPLY',initialResult,enteredNumber,currentResult);
}

const divide = () =>{
    const enteredNumber = getUserNumberInput();
    const initialResult = currentResult;
    currentResult /=  enteredNumber;
    createAndWriteOutput('/',initialResult,enteredNumber);
    writeToLog('DIVIDE',initialResult,enteredNumber,currentResult);
}


// currentResult = currentResult + 10;
let errorMessage = 'An error \n' + 'occurred!';
//wite-space : pre 로 설정하면 줄바꿈을 화면상에서 볼수있음.


addBtn.addEventListener('click',add);
subtractBtn.addEventListener('click',subtract);
multiplyBtn.addEventListener('click',multiply);
divideBtn.addEventListener('click',divide);
