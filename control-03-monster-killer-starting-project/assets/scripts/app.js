const ATTACK_VALUE = 10;
const STRONG_ATTACK_VALUE = 17;
const MONSTER_ATTACK_VALUE = 14;
const HEAL_VALUE = 20;

const MODE_ATTACK = 'ATTACK' // > 0
const MODE_STRONG_ATTACK = 'STRONG_ATTACK'; //>1

// let choseMaxLife = 100;
let currentMonsterHealth = choseMaxLife;
let currentPlayerHealth = choseMaxLife;
let hasBonusLife = true;

const enteredValue = prompt('최대 체력값을 입력해주세요.','100');

let choseMaxLife = parseInt(enteredValue);
if(isNaN(choseMaxLife) || choseMaxLife<=0){
    choseMaxLife = 100;
}

adjustHealthBars(choseMaxLife);

function endRound(){
    const initialPlayerHealth = currentPlayerHealth;
    const playerDamage = dealPlayerDamage(MONSTER_ATTACK_VALUE);
    currentPlayerHealth -= playerDamage;

    if(currentPlayerHealth <= 0 && hasBonusLife){
        hasBonusLife =false;
        removeBonusLife();
        currentPlayerHealth =initialPlayerHealth;
        alert('보너스 라이프를 사용하였습니다.');
        setPlayerHealth(initialPlayerHealth);
    }

    if(currentMonsterHealth<=0 && currentPlayerHealth >0){
        alert('You won!');
    }else if(currentPlayerHealth<=0 && currentMonsterHealth>0){
        alert('You lost!');
    }else if(currentMonsterHealth<=0 && currentPlayerHealth<=0){
        alert('You have a draw!');
    }
}

function attackMonster(mode){
    let maxDamage;
    if(mode === MODE_ATTACK){
        maxDamage = ATTACK_VALUE;
    }else if(mode === MODE_STRONG_ATTACK){
        maxDamage = STRONG_ATTACK_VALUE
    }
    const damage = dealMonsterDamage(maxDamage);
    currentMonsterHealth -= damage;
    endRound();
}

function attackHandler(){
    attackMonster(MODE_ATTACK);
}

function strongAttackHandler(){
    attackMonster(MODE_STRONG_ATTACK);
}
//변수 쉐도윙? 이게 뭐지
function healPlayerHandler() {
    let healValue;
    if(currentPlayerHealth>=choseMaxLife-HEAL_VALUE){
        currentPlayerHealth=choseMaxLife;
        alert("you can't heal to more than your max initial health.");
        healValue=choseMaxLife-currentPlayerHealth;
    }else{
        healValue = HEAL_VALUE;
    }
    increasePlayerHealth(healValue);
    currentPlayerHealth += healValue;
    endRound();
}

attackBtn.addEventListener('click',attackHandler);
strongAttackBtn.addEventListener('click',strongAttackHandler);
healBtn.addEventListener('click',healPlayerHandler);