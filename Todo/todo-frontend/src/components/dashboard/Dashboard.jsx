import React, {useContext, useEffect, useState} from 'react';
import './dashboard.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import {faEllipsisVertical, faLeftLong, faRightLong, faUser} from "@fortawesome/free-solid-svg-icons";
import {UserContext} from "../../context/UserContext.jsx";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import Dropdown from 'react-bootstrap/Dropdown';
import DropdownButton from 'react-bootstrap/DropdownButton';

const Dashboard = () => {
    const [date, setDate] = useState(new Date());
    const [description, setDescription] = useState("");
    const [isOpen, setIsOpen] = useState(false);
    const [todo, setTodo] = useState([]);
    const [loading, setLoading]= useState(false);
    const user = useContext(UserContext);

    useEffect(() => {
        if(!loading){
            setLoading(true);
            fetch("/api/todo/user/".concat(user.currentUser?.user_id),{
                method: "GET",
                headers: {'Authorization': 'Bearer '.concat(user.currentUser?.user_token)},
            }).then(res => res.json()).then(json => setTodo(json)).catch(err => {console.log(err); setLoading(false)});
        }
    }, []);

    console.log(todo);
    let addTask = async (e)=> {
        e.preventDefault();
        try{
            let res = await fetch("/api/todo",{
                method: "POST",
                body: JSON.stringify({
                    description: description,
                    dueDate: date.getFullYear()
                        +"-" +(date.getMonth() +1).toString().padStart(2, "0")
                        +"-"+date.getDate().toString().padStart(2, "0"),
                    isCompleted: false,
                    status: "InComplete",
                    secUser: {
                        id: user.currentUser?.user_id
                    }
                }),
                headers: {'Content-Type': 'application/json',
                    'Authorization': 'Bearer '.concat(user.currentUser?.user_token)},
            });
            if(res.status===201){
                setDescription("");
                alert("Task Added")
            }
            else{
                alert("Some Error Occurred")
            }
        }
        catch(err){
            console.log(err);
        }
    };

    let getPreviousDate = () => {
        const { selectedDate } = this.state

        const currentDayInMilli = new Date(selectedDate).getTime()
        const oneDay = 1000 * 60 *60 *24
        const previousDayInMilli = currentDayInMilli - oneDay
        const previousDate = new Date(previousDayInMilli)

        this.setDate(previousDate)

    }

    return (
      <div className="dashboard">
          <div className="dashboard-top">
              <div className="dashboard-top-left"></div>
              <div className="dashboard-top-middle"><h1>To do List</h1>
                  <h2>Welcome {user.currentUser?.user_name}</h2>
              </div>
              <div className="dashboard-top-right"><FontAwesomeIcon icon={faUser} /></div>
          </div>
          <div className="dashboard-bottom">
              <div className="dashboard-bottom-heading">
                  <div className="dashboard-bottom-heading-button"><button onClick={() => date.setDate(date.getDate() -1)}><FontAwesomeIcon icon={faLeftLong}></FontAwesomeIcon></button></div>
                  <div className="dashboard-bottom-heading-middle">
                      <h3><DatePicker className="dashboard-bottom-heading-middle-calender" selected={date} onChange={(date) => setDate(date)} /></h3><br />
                      <span>{date.toLocaleString("en", {weekday: "long"})}</span>
                  </div>
                  <div className="dashboard-bottom-heading-button"><button onClick={() => date.setDate(date.getDate() +1)}><FontAwesomeIcon icon={faRightLong}></FontAwesomeIcon></button></div>
              </div>
              <div className="dashboard-bottom-body">
                  <div className="dashboard-bottom-body-top">
                      <span><input type={"text"} placeholder="Add a task" onChange={(e)=> setDescription(e.target.value)}/></span>
                      <button onClick={addTask}>ADD</button>
                  </div>
                  <div className="dashboard-bottom-body-bottom">
                      <table className='dashboard-bottom-body-bottom-table'>
                          <thead className='dashboard-bottom-body-bottom-table-head'>
                          <tr>
                              <th></th>
                              <th>Description</th>
                              <th>Date</th>
                              <th>Status</th>
                          </tr>
                          </thead>
                          <tbody className='dashboard-bottom-body-bottom-table-body'>
                          {
                              todo.map((res,index) => {
                                  return <tr key={index}>
                                      <td className="dashboard-bottom-body-bottom-table-body-action">
                                          <DropdownButton id="dropdown-basic-button" title={<FontAwesomeIcon icon={faEllipsisVertical} />}>
                                              <Dropdown.Item href="#/action-1">ReSchedule</Dropdown.Item><br />
                                              <Dropdown.Item href="#/action-2">Mark Completed</Dropdown.Item>
                                          </DropdownButton>
                                      </td>
                                      <td>{res.description}</td>
                                      <td>{res.dueDate}</td>
                                      <td className='dashboard-bottom-body-bottom-table-body-status'>{res.complete===true? <span className='dashboard-bottom-body-bottom-table-body-status-pass'>Complete</span> : <span className='dashboard-bottom-body-bottom-table-body-status-fail'>InComplete</span>}</td>
                                  </tr>
                              })
                          }
                          </tbody>
                      </table>
                  </div>
              </div>
          </div>
      </div>
  )
}
export default Dashboard