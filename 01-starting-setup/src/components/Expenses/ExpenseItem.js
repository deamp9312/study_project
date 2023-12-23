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

  return (
    <Card className="expense-item">
        <ExpenseDate date={props.date}/>
      {/* <div>{formattedDate}</div> */}
      <div className="expense-item__description">
        <h2>{props.title}</h2>
        <div className="expense-item__price">${props.amount}</div>
      </div>
    </Card>
  );
}

export default ExpenseItem;
