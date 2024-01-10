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