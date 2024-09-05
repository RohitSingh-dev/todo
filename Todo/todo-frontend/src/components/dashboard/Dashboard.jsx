import React, {useContext, useEffect, useState} from 'react';
import './dashboard.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import {faEllipsisVertical, faLeftLong, faRightLong} from "@fortawesome/free-solid-svg-icons";
import {UserContext} from "../../context/UserContext.jsx";

const Dashboard = () => {
    const [isOpen, setIsOpen] = useState(false);
    const [secUser, setSecUser] = useState({});
    const [todo, setTodo] = useState([]);
    const [loading, setLoading]= useState(false);
    const user = useContext(UserContext);

    useEffect(() => {
        if(!loading){
            setLoading(true);
            fetch("/api/user/".concat(user.currentUser?.user_id),{
                method: "GET",
                headers: {'Authorization': 'Bearer '.concat(user.currentUser?.user_token)},
            }).then(res => res.json()).then(json => setSecUser(json)).catch(err => {console.log(err); setLoading(false)});
        }
    }, [])

  return (
      <div className="dashboard">
          <div className="dashboard-top">
              <h1>To do List</h1>
              <h2>Welcome {secUser.name}</h2>
          </div>
          <div className="dashboard-bottom">
              <div className="dashboard-bottom-heading">
                  <div className="dashboard-bottom-heading-button"><FontAwesomeIcon icon={faLeftLong}></FontAwesomeIcon></div>
                  <div className="dashboard-bottom-heading-middle">
                      <h3>03/09/2024</h3><br />
                      <span>Thursday</span>
                  </div>
                  <div className="dashboard-bottom-heading-button"><FontAwesomeIcon icon={faRightLong}></FontAwesomeIcon></div>
              </div>
              <div className="dashboard-bottom-body">
                  <div className="dashboard-bottom-body-top">
                      <span><input type={"text"} placeholder="Add a task"/></span>
                      <button>ADD</button>
                  </div>
                  <div className="dashboard-bottom-body-bottom">
                      <div className="action">
                          <button onClick={() => setIsOpen(!isOpen)}>
                              <FontAwesomeIcon icon={faEllipsisVertical} />
                          </button>
                          {isOpen && (
                              <div>
                                  <p>Reschedule</p>
                                  <p>Mark as Completed</p>
                              </div>
                          )}
                      </div>
                      <span>Description</span>
                      <span>10/09/2024</span>
                      <span>InComplete</span>
                  </div>
              </div>
          </div>
      </div>
  )
}
export default Dashboard