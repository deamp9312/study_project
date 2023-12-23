import React from 'react';
import Expense from "./components/Expenses/Expense"
//사용자 지정 컴포넌트는 대문자로 시작되는것을 규칙으로 함.
const App = () => {
  const expenses = [
    {
      id: "e1",
      title: "Toilet Paper1",
      amount: 123.111,
      date: new Date(2023, 11, 11),
    },
    {
      id: "e2",
      title: "Toilet Paper2",
      amount: 123.222,
      date: new Date(2023, 11, 12),
    },
  ];
// 반환하는 밑에랑 같은구조라고 생각하면됨 과거의 JSX 의 소스가 이런식이였음
  return React.createElement(
    'div',
    {},
    React.createElement('h2',{},'hi'),
    React.createElement(Expense,{expenses: expenses})
    );

  // return (
  //   <div>
  //       <h2>hi</h2>
  //   <Expense expenses={expenses}/>
  //   </div>
  // );
}

//JSX란
/** html = xml
 * JavaScript XML
 */

export default App;
