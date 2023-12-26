import React, {useState} from 'react';
import ExpenseItem from './ExpenseItem';
import './Expense.css';
import Card from '../UI/Card';
import ExpensesFilter from './ExpensesFilter';


const Expense = (props)=>{
  const [filteredYear ,setFilteredYear] = useState('2020');
  const [filterInfoText ,setFilterInfoText] = useState('2019,2021 & 2022');

  const filterChangeHandler = selectedYear =>{
    // console.log(selectedYear);
    setFilteredYear(selectedYear);
    if(selectedYear==='2019'){
      setFilterInfoText('2020,2021 & 2022');
    }else{
      setFilterInfoText('2019,2021 & 2022');
    }
    //
  };
  

    return (
      <div>
        <Card className="expenses">
           <ExpensesFilter selected={filteredYear} onChangeFilter ={filterChangeHandler}/>
            <p>Data for years {filterInfoText} is hidden.</p>
            <ExpenseItem
            title={props.expenses[0].title}
            amount={props.expenses[0].amount}
            date={props.expenses[0].date}
            />
            <ExpenseItem
            title={props.expenses[1].title}
            amount={props.expenses[1].amount}
            date={props.expenses[1].date}
            />
            <ExpenseItem
            title={props.expenses[1].title}
            amount={props.expenses[1].amount}
            date={props.expenses[1].date}
            />
            <ExpenseItem
            title={props.expenses[1].title}
            amount={props.expenses[1].amount}
            date={props.expenses[1].date}
            />
      </Card>
  </div>)
}

export default Expense;