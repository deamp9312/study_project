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
/**
 * performance 탭은 페이지 랜더링시 브라우저에서 실행하는 작업을
 * 자세하게 알수있고 이를통해 다양한 작업이 가능함, 스크립트가 어떻게 
 * 로드,분석 실행되었는지 방법을 이해할 수 있고 문제도 확인할수 잇음.
 * 
 */


function calculateResult(calculationType) {
    const enteredNumber = getUserNumberInput();
    const initialResult = currentResult;
    let mathOperator;
    if(calculationType === 'ADD'){
        currentResult += enteredNumber;
        mathOperator = '+';
    }else if(calculationType == 'SUBTRACT'){
        currentResult -= enteredNumber;
        mathOperator = '-';
    }else if(calculationType == 'MLTIPLY'){
        currentResult *= enteredNumber;
        mathOperator = '*';
    }else if(calculationType == 'DIVIDE'){
        currentResult /= enteredNumber;
        mathOperator = '/';
    }else{
        return;
    }
    createAndWriteOutput(mathOperator,initialResult,enteredNumber);
    writeToLog(calculationType,initialResult,enteredNumber,currentResult);
}

function add(){
    calculateResult('ADD');
    
}
const subtract = () =>{
    calculateResult('SUBTRACT');
}

const multiply = () =>{
    calculateResult('MLTIPLY');
}

const divide = () =>{
    calculateResult('DIVIDE');
}


// currentResult = currentResult + 10;
let errorMessage = 'An error \n' + 'occurred!';
//wite-space : pre 로 설정하면 줄바꿈을 화면상에서 볼수있음.


addBtn.addEventListener('click',add);
subtractBtn.addEventListener('click',subtract);
multiplyBtn.addEventListener('click',multiply);
divideBtn.addEventListener('click',divide);
