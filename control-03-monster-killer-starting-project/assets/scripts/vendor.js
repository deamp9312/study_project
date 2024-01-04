const monsterHealthBar = document.getElementById('monster-health');
const playerHealthBar = document.getElementById('player-health');
const bonusLifeEl = document.getElementById('bonus-life');

const attackBtn = document.getElementById('attack-btn');
const strongAttackBtn = document.getElementById('strong-attack-btn');
const healBtn = document.getElementById('heal-btn');
const logBtn = document.getElementById('log-btn');

function adjustHealthBars(maxLife) {
  monsterHealthBar.max = maxLife;
  monsterHealthBar.value = maxLife;
  playerHealthBar.max = maxLife;
  playerHealthBar.value = maxLife;
}

function dealMonsterDamage(damage) {
  const dealtDamage = Math.random() * damage;
  monsterHealthBar.value = +monsterHealthBar.value - dealtDamage;
  return dealtDamage;
}

function dealPlayerDamage(damage) {
  const dealtDamage = Math.random() * damage;
  playerHealthBar.value = +playerHealthBar.value - dealtDamage;
  return dealtDamage;
}

function increasePlayerHealth(healValue) {
  playerHealthBar.value = +playerHealthBar.value + healValue;
}

function resetGame(value) {
  playerHealthBar.value = value;
  monsterHealthBar.value = value;
}

function removeBonusLife() {
  bonusLifeEl.parentNode.removeChild(bonusLifeEl);
}

function setPlayerHealth(health) {
  playerHealthBar.value = health;
}

/**
 * 호이스팅 -> 자바스크립트는 실행하면 전체 스크립트를 확인해서 함수를 찾은 뒤 자로등으로 로드하고
 * 등록해서 실제 사용하는 코드 아래에 함수를 작성하도록 하는 것(let,const,var)
 * 
 * 따라서 아래의 예시는 실행시점에 변경됨, 그러나 let,const는 값을 아예 할당하지않아 오류가 발생
 * 하지만 var은 undefined로 값을 설정해줘서 로그 찍을때 undefined값이 찍히게 되는거
 * 
 * ------예시가 실행시점에 변경되는거 ---
 * var userName;
 * console.log(userName);
 * userName = "cho";
 * ------예시
 * console.log(userName);
 * var userName = "cho";
 * -------
 */