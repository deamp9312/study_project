import React, { useState } from 'react';

import './ExpenseForm.css';

const ExpenseForm = () =>{
    const [enteredTitle,setEnteredTitle] = useState('');
    const [enteredAmount,setEnteredAmount] = useState('');
    const [enteredDate,setEnteredDate] = useState('');
    
    //상태를 하나로 선언하기
    // const [userInput,setUserInput]= useState({
    //     enteredTitle :'',
    //     enteredAmount :'',
    //     enteredDate :''
    // });

    // 바닐라 Js
    // document.getElementById('').addEventListener('click',(event)=>{})

    const titleChangeHandler = (event)=>{
        setEnteredTitle(event.target.value);
        //1.리액트는 상태 업데이트를 예약함 즉시 처리하지 않음 따라서
        // 다수의 상태 업데이트를 동시에 예약할 경우 오래되었거나 잘못된 스냅샷에 의존하게 될수도 있음
        // setUserInput({
        //     ...userInput,
        //     enteredTitle : event.target.value;
        // })
        //2.리액트가 이 내부 함수에서 자공하는 스냅샷이 최신 상태 스냅샷이 되도록 보장해줌.(예약된 모든 상태 업데이트를 기억하고서)
        // setUserInput((prevState)=>{
        //     return {...userInput,
        //         enteredTitle : event.target.value};
        // })
    }
    const amountChangeHandler = (event)=>{
        setEnteredAmount(event.target.value);
        // setUserInput({
        //     ...userInput,
        //     enteredAmount : event.target.value;
        // })
    }
    const dateChangeHandler = (event)=>{
        setEnteredDate(event.target.value);
        // setUserInput({
        //     ...userInput,
        //     enteredDate : event.target.value;
        // })
    }
    const inputChangeHandler = (identifier,value)=>{
        if(identifier==='title'){
            setEnteredTitle(value);
        }else if(identifier === 'date'){
            setEnteredDate(value);
        }else if(identifier === 'amount'){
            setEnteredAmount(value);
        }

    }


    return (
        <form>
            <div className='new-expense__controls'>
                <div className='new-expense__control'>
                    <label>Title</label>
                    <input type="text" onChange={(event)=> inputChangeHandler('title',event.target.value)}/>
                </div>
            </div>
            <div className='new-expense__controls'>
                <div className='new-expense__control'>
                    <label>Amount</label>
                    <input type="number" min="0.01" step="0.01" onChange={(event)=> inputChangeHandler('amount',event.target.value)}/>
                </div>
            </div>
            <div className='new-expense__controls'>
                <div className='new-expense__control'>
                    <label>Date</label>
                    <input type="date" min="2019-01-01" max="2023-12-31" onChange={(event)=> inputChangeHandler('date',event.target.value)}/>
                </div>
            </div>
            <div className='new-expense__actions'>
                <button type='submit'>Add Expense</button>
            </div>
        </form>
    )

} 
export default ExpenseForm;