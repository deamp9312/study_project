import React, { useState } from 'react';

//리액트의 컴포넌트는 단지 자바스크립트의 함수에 불가함.
//반환되는게 JSX라는게 특이하지만 js fn이라는게 핵심.

//반환되는 값은 루트요소가 오직 하나만 
//반환되어야 됨(아래 외부의 div 테그가 없으면 문제발생함)
import './ExpenseItem.css';
import ExpenseDate from './ExpenseDate';
import Card from '../UI/Card';

const ExpenseItem = (props) =>{
// function ExpenseItem({date,title,amount}) { //객체로 하나씩 끌어다가 사용도 가능
    // const expenseDate = new Date(2023,11,18);
    // const expenseTitle = "Car Insurance";
    // const expenseAmout = 294.12;
    
    // const formattedDate = props.expenses && props.expenses.date ? props.expenses.date.toISOString() : "Invalid Date";

    // const month = props.date.toLocaleString("ko-kr",{month:'long'});
    // const year = props.date.getFullYear();
    // const day = props.date.toLocaleString("ko-kr",{day:'2-digit'});

    
    // let title =props.title;
    const clickHandler = () =>{
      //해당 함수의 뒤에 ()를 붙여서 기존 함수사용하는것처럼 안 쓰는이유는 페이지 로딩시점에
      //실행되어 버튼도 안 눌렀는데 이미 로딩되면서 실행되는 문제점이 생기기 때문
      //따라서 지목하기만 해둬도 리액트에서 해당 함수를 기억해두었다가 대신 함수를 실행해줌.
      // title='Updated!!';
      //그러나 위와같은 코드는 리액트에서 처음 함수단위로 리턴되면서 처음 셋팅한뒤에
      //재실행 되지는 않기때문에 타이틀문구가 변경이 안됨.
      //그걸 해결하기위한 방법으로 상태라는 동작원리가 존재함.
      console.log(title);

      setTitle('updated!!');
    }

    //useState는 리액트 훅으로 함수내부에 사용
    //배열 첫번째는 관리되는 해당 값에 대한 포인터
    //나중에 새 품명ㅇ을 설정할 때 호출할 함수입니다.
    const [title,setTitle] = useState(props.title);

    

  return (
    <Card className="expense-item">
        <ExpenseDate date={props.date}/>
      {/* <div>{formattedDate}</div> */}
      <div className="expense-item__description">
        <h2>{title}</h2>
        <div className="expense-item__price">${props.amount}</div>
      </div>
      <button onClick = {clickHandler}>Change Title</button>
    </Card>
  );
}

export default ExpenseItem;
