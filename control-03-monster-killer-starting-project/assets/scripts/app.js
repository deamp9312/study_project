const ATTACK_VALUE = 10;
const STRONG_ATTACK_VALUE = 17;
const MONSTER_ATTACK_VALUE = 14;
const HEAL_VALUE = 20;

let choseMaxLife = 100;
let currentMonsterHealth = choseMaxLife;
let currentPlayerHealth = choseMaxLife;
let hasBonusLife = true;

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
    if(mode === 'ATTACK'){
        maxDamage = ATTACK_VALUE;
    }else if(mode === 'STRONG_ATTACK'){
        maxDamage = STRONG_ATTACK_VALUE
    }
    const damage = dealMonsterDamage(maxDamage);
    currentMonsterHealth -= damage;
    endRound();
}

function attackHandler(){
    attackMonster('ATTACK');
}

function strongAttackHandler(){
    attackMonster('STRONG_ATTACK');
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