let date = new Date();

const renderCalendar = () => {
  const viewYear = date.getFullYear();
  const viewMonth = date.getMonth();

  document.querySelector('.year-month').textContent = `${viewYear}년 ${viewMonth + 1}월`;

  const prevLast = new Date(viewYear, viewMonth, 0);
  const thisLast = new Date(viewYear, viewMonth + 1, 0);

  const PLDate = prevLast.getDate();
  const PLDay = prevLast.getDay();

  const TLDate = thisLast.getDate();
  const TLDay = thisLast.getDay();

  const prevDates = [];
  const thisDates = [...Array(TLDate + 1).keys()].slice(1);
  const nextDates = [];

  if (PLDay !== 6) {
    for (let i = 0; i < PLDay + 1; i++) {
      prevDates.unshift(PLDate - i);
    }
  }

  for (let i = 1; i < 7 - TLDay; i++) {
    nextDates.push(i);
  }

  const dates = prevDates.concat(thisDates, nextDates);
  const firstDateIndex = dates.indexOf(1);
  const lastDateIndex = dates.lastIndexOf(TLDate);

  dates.forEach((date, i) => {
    const condition = i >= firstDateIndex && i < lastDateIndex + 1 ? 'this' : 'other';
    dates[i] = `<div class="date"><span class=${condition}>${date}</span></div>`;
  });

  document.querySelector('.dates').innerHTML = dates.join('');

  const today = new Date();
  if (viewMonth === today.getMonth() && viewYear === today.getFullYear()) {
    for (let date of document.querySelectorAll('.this')) {
      if (+date.innerText === today.getDate()) {
        date.classList.add('today');
        break;
      }
    }
  }

  const addDataToCell = (button) => {
    const dateValue = button.innerText;
    // 여기에 날짜 칸에 데이터를 추가하는 로직을 작성하세요.
    console.log(`Data added to cell: ${dateValue}`);
    // 새로운 HTML 페이지로 이동
    // window.location.href = '새로운페이지.html';
  };

  const dateButtons = document.querySelectorAll('.date');
  dateButtons.forEach((button) => {
    const dateValue = button.querySelector('span').innerText;
    const inputButton = document.createElement('button');
    inputButton.innerText = '음식 추가하기';
    inputButton.classList.add('input-btn');
    inputButton.addEventListener('click', () => addDataToCell(dateValue));
    
    const lineBreak = document.createElement('br'); // 라인 스킵을 위한 <br> 요소 생성
  	button.appendChild(lineBreak); // <br> 요소를 날짜 칸에 추가
    button.appendChild(inputButton); // 버튼을 날짜 칸에 추가
  });
};

renderCalendar();

const prevMonth = () => {
  date.setMonth(date.getMonth() - 1);
  renderCalendar();
};

const nextMonth = () => {
  date.setMonth(date.getMonth() + 1);
  renderCalendar();
};

const goToday = () => {
  date = new Date();
  renderCalendar();
};
