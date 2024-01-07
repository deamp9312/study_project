const startGameBtn = document.getElementById("start-game-btn");

const ROCK = "ROCK"; // 바위
const PAPER = "PAPER"; // 보
const SCISSORS = "SCISSORS"; // 가위
const DEFAULT_USER_CHOICE = ROCK;
const RESULT_DRAW = 'DRAW';
const RESULT_PLAYER_WINS = 'PLAYER_WINS';
const RESULT_COMPUTER_WINS = 'COMPUTER_WINS';

let gameIsRunning = false;

const getPlayerChoice = function () {
  const selection = prompt("가위 바위 보 ?", "").toUpperCase();
  if (
    selection !== "ROCK" &&
    selection !== "PAPER" &&
    selection !== "SCISSORS"
  ) {
    alert(`기본값인 묵(${DEFAULT_USER_CHOICE})으로 설정되었습니다.`);
    return DEFAULT_USER_CHOICE;
  }
  return selection;
};

const getComputerChoice = function(){
    const randomValue = [1,2,3][(Math.random()*3)];
    if(randomValue === 1){
        return ROCK;
    }else if(randomValue ===2){
        return PAPER;
    }else{
        return SCISSORS;
    }
}
//기본변수 설정
const getWinner = function(cChoice,pChoice = DEFAULT_USER_CHOICE){
    if(cChoice === pChoice){
        return RESULT_DRAW;
    }
}

startGameBtn.addEventListener("click", function () {
  if (gameIsRunning) {
    return;
  }
  gameIsRunning = true; 
  console.log("게임 시작 !!!");
  const playerSelection = getPlayerChoice();
  console.log(playerSelection);
  const computerChoice = getComputerChoice();
});

// function startGame(){
//     console.log('게임시작');
// }

// const person = {
//     name : 'cho', // 객체에 들어가는 변수 name이 프로퍼티가 되는것과 같이 변수에 값을 저장하는 원리와 같음
//     greet : function greet(){ //프로퍼티에 함수를 저장할때 키나 객체에 저장된 함수를 메서드라고 부름
//         console.log('greet log!');
//         //객체에 연결된 함수가 메서드라고 명칭함.
//     }
// };

// person.greet();
// console.dir( startGame); //함수에 나오는 모든 프로퍼티 이고 key value 의 쌍으로 되어있음
//따라서 함수도 객체임!!!!! 또한 객체는 힙에 저장됨

// startGameBtn.addEventListener('click',startGame);
// startGameBtn.addEventListener('click',person.greet);

//익명함수를 사용하면 기존 에러는 <anonymous> 에러로 표기해주는 이럴때 익명함수가 여럿이면
//라인을 제공한다하더라도 명확하지 않을수도 있음, 그러나 익명함수로 이름을 짓게되면 해당 함수명을
//반환하여 명확하게 어느곳에서 문제가 되었는지 발견하기 쉬움.
// startGameBtn.addEventListener('click',function startGame(){
//     console.log('error is here!!!!', age);
// });


const combine = (resultHandler,operation,...numbers) =>{//Rest 연산자

    let sum = 0;
    //함수안의 내부 함수
    const validateNumber = (number) =>{
        return isNaN(number) ? 0 : number;
    };
    
    for(const num of numbers){
        if(operation === 'ADD'){
            sum += validateNumber(num);
        }else{
            sum -= validateNumber(num);
        }
    }
    //콜백 함수 사용하기
     resultHandler(sum,'COM결과는 ');
}

const sumUp = (resultHandler,...numbers) =>{//Rest 연산자

    let sum = 0;
    //함수안의 내부 함수
    const validateNumber = (number) =>{
        return isNaN(number) ? 0 : number;
    };
    
    for(const num of numbers){
        sum += validateNumber(num);
    }
    //콜백 함수 사용하기
     resultHandler(sum,'결과는 ');
}
const substractUp = function(resultHandler,...numbers){
    let sum = 0;
    for(const num of numbers){ // 익명함수일때 사용할수 있지만 사용하지 않는것을 추천함.
        sum-= num;
    }
    resultHandler(sum,'결과는 ');
}
const showHandler = (result, resultMsg) =>{
    console.log(resultMsg + ' : '+ result);
}

sumUp(showHandler,1,2,3,4,5,6,7,8,10,'123ㅇㄴ');
substractUp(showHandler,1,2,3,4,5,6,7,8,10);


combine(showHandler,'ADD',1,2,3,4,5,6,7,8,10,'123ㅇㄴ');
combine(showHandler,'',1,2,3,4,5,6,7,8,10);