const startGameBtn = document.getElementById('start-game-btn');

function startGame(){
    console.log('게임시작');
}

const person = {
    name : 'cho', // 객체에 들어가는 변수 name이 프로퍼티가 되는것과 같이 변수에 값을 저장하는 원리와 같음
    greet : function greet(){ //프로퍼티에 함수를 저장할때 키나 객체에 저장된 함수를 메서드라고 부름
        console.log('greet log!');
        //객체에 연결된 함수가 메서드라고 명칭함.
    }
};

// person.greet();
console.dir( startGame); //함수에 나오는 모든 프로퍼티 이고 key value 의 쌍으로 되어있음
//따라서 함수도 객체임!!!!! 또한 객체는 힙에 저장됨

// startGameBtn.addEventListener('click',startGame);
// startGameBtn.addEventListener('click',person.greet);

//익명함수를 사용하면 기존 에러는 <anonymous> 에러로 표기해주는 이럴때 익명함수가 여럿이면
//라인을 제공한다하더라도 명확하지 않을수도 있음, 그러나 익명함수로 이름을 짓게되면 해당 함수명을
//반환하여 명확하게 어느곳에서 문제가 되었는지 발견하기 쉬움.
startGameBtn.addEventListener('click',function startGame(){
    console.log('error is here!!!!', age);
});