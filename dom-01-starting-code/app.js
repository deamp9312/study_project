// window.alert('f12개발자도구에서는 window를 붙어야되지만 여기에서는 자동으로 생략되어 사용가능')
// // console.dir(window.document);

/**
 * html의 노드에 맞게 구조가 만들어짐
 * <html> <head> <title> <body> <header><h1>....
 * 
 * querySelectorAll은 정적 Nodelist 즉 현재 렌더링 된 DOM의 스냅샷을 제공하는 반면
 * getElementsByTagName와 같은 종류의 메서드는 동적 nodeLIst를 반환합니다,
 * 즉 요소를 추가하거나 제거하는 경우 get/By 메서드를 통해 불러온 목록에 반영이 됩니다.
 * 
 * 노드 & 요소 이해하기... 149강 다시 듣자 나중에...
 */



 
//154. 다수의 요소 가져오기
const allLis = document.querySelectorAll('li');
//위의 방법과 비슷하지만 요소의 변경 사항을 반영하느 실시간 목록을 제공함.
const listItemAllList = document.getElementsByTagName('li');

for(const listItemEl of allLis){
    console.dir(listItemEl);
}

//
const h1 = document.getElementById('main-title')


h1.textContent = 'Change new Title';
h1.style.color = 'white';
h1.style.backgroundColor = 'black';

//마지막으로부터 한개 가져오는거
const li = document.querySelector('li:last-of-type');
li.textContent +=' change!';

const body = document.body

// document.querySelector('#Id 이름')와 document.getElementById('Id 이름')의 차이점은 무엇일까요?
// querySelector는 css 선택자로 (제공된 선택자에 따라) 모든 요소와 일치할 수 있으며
// getElementById는 Id로만 찾습니다.

//155. 돔 탐색 Traversing the DOM
{/* <div>
    <p>
        a<em>test!</em>
    </p>
</div> */}
/**
 * child : <p>는 <div>의 자식임
 * descendant : <div>의 후손은 <p> , <em> 두개임
 * Parent : <div> 는 <p>의 부모임
 * ancestor : <em>의 조상은 P,div enrodla
 * 
 * document.body <- <div> 이 관계에서
 * => parentNode(조상),parentElement(바로 윗 부모만) , closest()->직접 부모말고도 특정 css 쿼리와 일치하는 모든 조상을 사용하는 메서드
 * 
 * <div> -> 하위 속성들
 * => childNodes (자손들) , children , querySelector(), ..
 * => firstChild (첫번째 자식 노드를 선택) , firstElementChild ( 주어진 요소에서 하위요소중 첫번째 자식노드를 선택 )
 * => lasthild (하위노드 마지막꺼를 선택), lastElementChild (위에꺼 반대) 
 * 
 * 같은 레벨의 형제 노드를 찾아볼때
 * => previousSibling , previousElementSibling
 * 현재 선택된 요소의 전과 후 모두에서 형제요소를 구할 수 있도록 양방향으로 작용
 * => nextSibling, nextElementSibling
 * 
 * */

//156 자식 노드 검색하기
const ul = document.querySelector('ul')
ul.children[1]; // 자식 요소 노드만을 선택할때
ul.childNodes; // 빈 공백같은것도 포함된 모든 하위 자손 요소들 반환
document.querySelector('li:last-of-type')

ul.firstChild  //이경우 텍스트 노드임
ul.firstElementChild

//157. 부모 노드 검색하기
const firstLi = document.querySelector('li')
firstLi.parentNode
//부모노드가 요소 노드여야 자식 노드를 가질수 있음(텍스트 노드는 자식 노드를 가질수없음)


//160 dom 요소 스타일링
const section = document.querySelector('section');
const button = document.querySelector('button');
button.addEventListener('click',()=>{ //버튼으로 특정요소 활성화 비활성화 조절하기
    section.classList.toggle('visible')
    section.classList.toggle('invisible')

});