// const userInput = document.getElementById('input-number');
// const addBtn = document.getElementById('btn-add');
// const subtractBtn = document.getElementById('btn-subtract');
// const multiplyBtn = document.getElementById('btn-multiply');
// const divideBtn = document.getElementById('btn-divide');

// const currentResultOutput = document.getElementById('current-result');
// const currentCalculationOutput = document.getElementById('current-calculation');

// function outputResult(result, text) {
//   currentResultOutput.textContent = result;
//   currentCalculationOutput.textContent = text;
// }


let defaultResult = 0 ;
let currentResult = defaultResult;

function add(num1,num2){
    const result = num1+num2;
    alert('result is ' + result);
}

let calculationDescription = `hihi ${currentResult}`;

currentResult = currentResult + 10;
let errorMessage = 'An error \n' + 'occurred!';
//wite-space : pre로 설정하면 줄바꿈을 화면상에서 볼수있음.


outputResult(currentResult,errorMessage)