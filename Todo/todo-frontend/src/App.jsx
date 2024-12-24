import './App.css'
import {Dashboard, LoginPage, Register} from "./components/index.js";
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import ScreenshotButton from "./components/screenshotButtonPage/ScreenshotButton.jsx";

function App() {

  return (
      <BrowserRouter>
          <Routes>
              <Route path='/' element={<LoginPage />} />
              <Route path='/auth/login' element={<LoginPage />} />
              <Route path='/user/register' element={<Register />} />
              <Route path='/dashboard' element={<Dashboard />} />
              <Route path='/take-screenshot' element={<ScreenshotButton />} />
          </Routes>
      </BrowserRouter>
  )
}

export default App
