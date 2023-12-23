import ReactDOM from 'react-dom/client';

import './index.css';
import App from './App'; 
// App의 뒤의 js확장자명을 생략해야된다.but css는 생략하면 안됨.
const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(<App />);

