import React, { useState } from 'react';

import './ExpenseForm.css';

const ExpenseForm = () =>{
    // const [enteredTitle,setEnteredTitle] = useState('');
    // const [enteredAmount,setEnteredAmount] = useState('');
    // const [enteredDate,setEnteredDate] = useState('');
    
    //상태를 하나로 선언하기
    const [userInput,setUserInput]= useState({
        enteredTitle :'',
        enteredAmount :'',
        enteredDate :''
    });

    // 바닐라 Js
    // document.getElementById('').addEventListener('click',(event)=>{})
    
    const titleChangeHandler = (event)=>{
        // setEnteredTitle(event.target.value);
        setUserInput({
            ...userInput,
            enteredTitle : event.target.value;
        })
    }
    const amountChangeHandler = (event)=>{
        setUserInput({
            ...userInput,
            enteredAmount : event.target.value;
        })
    }
    const dateChangeHandler = (event)=>{
        setUserInput({
            ...userInput,
            enteredDate : event.target.value;
        })
    }

    return (
        <form>
            <div className='new-expense__controls'>
                <div className='new-expense__control'>
                    <label>Title</label>
                    <input type="text" onChange={titleChangeHandler}/>
                </div>
            </div>
            <div className='new-expense__controls'>
                <div className='new-expense__control'>
                    <label>Amount</label>
                    <input type="number" min="0.01" step="0.01" onChange={amountChangeHandler}/>
                </div>
            </div>
            <div className='new-expense__controls'>
                <div className='new-expense__control'>
                    <label>Date</label>
                    <input type="date" min="2019-01-01" max="2023-12-31" onChange={dateChangeHandler}/>
                </div>
            </div>
            <div className='new-expense__actions'>
                <button type='submit'>Add Expense</button>
            </div>
        </form>
    )

} 
export default ExpenseForm;