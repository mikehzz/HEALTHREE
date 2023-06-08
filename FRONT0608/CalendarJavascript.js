/**
 * 달력
 */
let date = new Date();

const renderCalendar = () => { // 달력 렌더링: 현재 날짜의 연도와 월 화면에 표시
  const viewYear = date.getFullYear();
  const viewMonth = date.getMonth();

  document.querySelector('.year-month').textContent = `${viewYear}년 ${viewMonth + 1}월`;

  const prevLast = new Date(viewYear, viewMonth, 0); // 이전 달의 마지막 날
  const thisLast = new Date(viewYear, viewMonth + 1, 0); // 현재 달의 마지막 날

  const PLDate = prevLast.getDate(); // 이전 달의 마지막 날짜
  const PLDay = prevLast.getDay(); // 이전 달의 마지막 요일

  const TLDate = thisLast.getDate(); // 현재 달의 마지막 날짜
  const TLDay = thisLast.getDay(); // 현재 달의 마지막 요일

  const prevDates = []; // 이전 달의 날짜 배열
  const thisDates = [...Array(TLDate + 1).keys()].slice(1); // 현재 달의 날짜 배열
  const nextDates = []; // 다음 달의 날짜 배열

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

  document.querySelector('.dates').innerHTML = dates.join(''); // HTML 요소로 변환

  const today = new Date(); // 오늘 날짜 표시
  if (viewMonth === today.getMonth() && viewYear === today.getFullYear()) {
    for (let date of document.querySelectorAll('.this')) {
      if (+date.innerText === today.getDate()) {
        date.classList.add('today');
        break;
      }
    }
  }

  /**
   * 버튼 클릭 시 해당 날짜에 데이터 추가
   */
  const addDataToCell = (button) => {
    const dateValue = button.innerText;
    console.log(`Data added to cell: ${dateValue}`);
    window.location.href = 'diary.html';
  };

  const dateButtons = document.querySelectorAll('.date');
  dateButtons.forEach((button) => {
    const dateValue = button.querySelector('span').innerText;
    const inputButton = document.createElement('button');
    inputButton.innerText = '음식 추가하기';
    inputButton.classList.add('input-btn');

    // 날짜 버튼을 클릭할 때마다 addDataToCell 함수 호출    
    inputButton.addEventListener('click', () => addDataToCell(dateValue));

    const lineBreak = document.createElement('br');
    button.appendChild(lineBreak);
    button.appendChild(inputButton);
  });

  /**
   * 테이블 값 변경
   */
  const updateTable = () => {
    /* 일단 무작위 값 */
    const dailyAvgKcal = Math.floor(Math.random() * 1000); // 일일 평균 칼로리
    const dailyAvgCarbs = Math.floor(Math.random() * 100); // 일일 평균 탄수화물
    const dailyAvgProtein = Math.floor(Math.random() * 100); // 일일 평균 단백질
    const dailyAvgFat = Math.floor(Math.random() * 100); // 일일 평균 지방

    const monthlySumKcal = Math.floor(Math.random() * 5000); // 월별 합산 칼로리
    const monthlySumCarbs = Math.floor(Math.random() * 500); // 월별 합산 탄수화물
    const monthlySumProtein = Math.floor(Math.random() * 500); // 월별 합산 단백질
    const monthlySumFat = Math.floor(Math.random() * 500); // 월별 합산 지방

    const tableCells = document.querySelectorAll('.summary_table td');
    tableCells[0].textContent = dailyAvgKcal;
    tableCells[3].textContent = dailyAvgCarbs;
    tableCells[6].textContent = dailyAvgProtein;
    tableCells[9].textContent = dailyAvgFat;

    tableCells[12].textContent = monthlySumKcal;
    tableCells[15].textContent = monthlySumCarbs;
    tableCells[18].textContent = monthlySumProtein;
    tableCells[21].textContent = monthlySumFat;
  };

  updateTable();
};

renderCalendar();

// 이전 달로 이동
const prevMonth = () => {
  date.setMonth(date.getMonth() - 1);
  renderCalendar();
};

// 다음 달로 이동
const nextMonth = () => {
  date.setMonth(date.getMonth() + 1);
  renderCalendar();
};

// 오늘 날짜로 이동
const goToday = () => {
  date = new Date();
  renderCalendar();
};