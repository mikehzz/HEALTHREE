/*
 * 탭 
 */
const tabList = document.querySelectorAll('.tab_menu .list li');
const contents = document.querySelectorAll('.tab_menu .cont_area .cont')
let activeCont = ''; // 현재 활성화 된 컨텐츠 (기본: #tab1 활성화)

for (var i = 0; i < tabList.length; i++) {
	tabList[i].querySelector('.btn').addEventListener('click', function(e) {
		e.preventDefault();
		for (var j = 0; j < tabList.length; j++) {
			tabList[j].classList.remove('is_on'); // 나머지 버튼 클래스 제거
			contents[j].style.display = 'none'; // 나머지 컨텐츠 display:none 처리
		}

		// 버튼 관련 이벤트
		this.parentNode.classList.add('is_on');

		// 버튼 클릭시 컨텐츠 전환
		activeCont = this.getAttribute('href');
		document.querySelector(activeCont).style.display = 'block';
	});
}



/*
 * 칼로리 그래프
 */
// 선택한 옵션에 따라 그래프를 업데이트하는 함수
const updateTab1Chart = () => {
	const selectElement = document.querySelector('.cal_dropdown');
	const selectedOption = selectElement.value;

	// 이번 주
	if (selectedOption === 'this_week') {
		new Chart(document.getElementById("caloryChart"), {
			type: 'line',
			data: {
				labels: ['5/22', '5/23', '5/24', '5/25', '5/26', '5/27', '5/28'], // x축, 마지막 날짜가 today가 되어야 함 
				datasets: [{
					data: [
						1950,
						2100,
						2040,
						1950,
						2190,
						2160,
						2250
					],
					label: 'Calory',
					borderColor: '#88C6FC',
					fill: false, // 그래프 아래 영역을 채울 지 여부
					lineTension: 0 // 그래프의 곡선 강도
				}]
			},
			options: {
				responsive: true, // 그래프 크기 반응형으로 조정 (dafalut : true)
				maintainAspectRatio: true, // 그래프 종횡비 유지 (defalut : true)
				legend: { display: false }, // 레이블 숨김
				tooltips: { // 마우스 호버 시 표시되는 툴팁 옵션
					mode: 'index',
					intersect: false, // 마우스가 근처에만 있어도 표현됨
				},
				hover: { // 호버 상호작용 옵션
					mode: 'nearest',
					intersect: true
				},
				animation: {
					duration: 0, // 애니메이션 지속 시간을 0으로 설정
					easing: null // 가속도 함수 제거
				},
				scales: {
					yAxes: [{
						display: true,
						ticks: {
							callback: function(value, index, values) {
								return value + 'kcal';
							}
						}
					}]
				}
			}
		});
		// 저번 주
	} else if (selectedOption === 'last_week') {
		new Chart(document.getElementById("caloryChart"), {
			type: 'line',
			data: {
				labels: ['5/15', '5/16', '5/17', '5/18', '5/19', '5/20', '5/21'], // x축, 마지막 날짜가 today가 되어야 함 
				datasets: [{
					data: [
						1900,
						2000,
						2020,
						1970,
						2150,
						2180,
						2200
					],
					label: 'Calory',
					borderColor: '#c9f06f',
					fill: false, // 그래프 아래 영역을 채울 지 여부
					lineTension: 0 // 그래프의 곡선 강도
				}]
			},
			options: {
				responsive: true, // 그래프 크기 반응형으로 조정 (dafalut : true)
				maintainAspectRatio: true, // 그래프 종횡비 유지 (defalut : true)
				legend: { display: false }, // 레이블 숨김
				tooltips: { // 마우스 호버 시 표시되는 툴팁 옵션
					mode: 'index',
					intersect: false, // 마우스가 근처에만 있어도 표현됨
				},
				hover: { // 호버 상호작용 옵션
					mode: 'nearest',
					intersect: true
				},
				animation: {
					duration: 0, // 애니메이션 지속 시간을 0으로 설정
					easing: null // 가속도 함수 제거
				},
				scales: {
					yAxes: [{
						display: true,
						ticks: {
							callback: function(value, index, values) {
								return value + 'kcal';
							}
						}
					}]
				}
			}
		});
	}
};

// select 요소의 변경 이벤트 핸들러 등록
const selectElementTab1 = document.querySelector('.cal_dropdown');
selectElementTab1.addEventListener('change', updateTab1Chart);

// 이번 주 옵션을 선택된 상태로 설정
selectElementTab1.value = 'this_week';

// 초기 로딩 시 그래프 업데이트
updateTab1Chart();



/*
 * 영양소 그래프
 */
// 선택한 옵션에 따라 그래프를 업데이트하는 함수
const updateTab2Chart = () => {
	const selectElement = document.querySelector('.nu_dropdown');
	const selectedOption = selectElement.value;
	const tableCells = document.querySelectorAll('.nu_table td');

	// 이번 주
	if (selectedOption === 'this_week') {
		new Chart(document.getElementById("nutrientChart"), {
			type: 'bar',
			data: {
				labels: ['5/22', '5/23', '5/24', '5/25', '5/26', '5/27', '5/28'], // x축
				datasets: [{
					label: '탄수화물',
					backgroundColor: '#D7CCC8',
					data: [
						324,
						350,
						327,
						310,
						330,
						360,
						350
					]
				}, {
					label: '단백질',
					backgroundColor: '#88C6FC',
					data: [
						55,
						58,
						60,
						57,
						63,
						59,
						55
					]
				}, {
					label: '지방',
					backgroundColor: '#c9f06f',
					data: [
						52,
						54,
						55,
						53,
						50,
						48,
						50
					]
				}
				]
			},
			options: {
				responsive: true, // 그래프 크기 반응형으로 조정 (dafalut : true)
				tooltips: { // 마우스 호버 시 표시되는 툴팁 옵션
					mode: 'index',
					intersect: false // 마우스가 근처에만 있어도 표현됨
				},
				hover: { // 호버 상호작용 옵션
					mode: 'nearest',
					intersect: true
				},
				animation: {
					duration: 0, // 애니메이션 지속 시간을 0으로 설정
					easing: null // 가속도 함수 제거
				},
				scales: {
					xAxes: [{
						display: true,
						stacked: true
					}],
					yAxes: [{
						display: true,
						stacked: true,
						ticks: {
							callback: function(value, index, values) {
								return value + 'g';
							}
						}
					}]
				}
			}
		});
		
		const thisCarbs = '?'; // 이번 주 탄수화물
    const thisProtein = '?'; // 이번 주 단백질
    const thisFat = '?'; // 이번 주 지방
    const thisSugar = '?'; // 이번 주 당류
    const thisNa = '?'; // 이번 주 나트륨
    const thisCole = '?'; // 이번 주 콜레스테롤

    tableCells[0].textContent = thisCarbs;
    tableCells[2].textContent = thisProtein;
    tableCells[4].textContent = thisFat;
    tableCells[6].textContent = thisSugar;
    tableCells[8].textContent = thisNa;
    tableCells[10].textContent = thisCole;

		// 저번 주
	} else if (selectedOption === 'last_week') {
		new Chart(document.getElementById("nutrientChart"), {
			type: 'bar',
			data: {
				labels: ['5/15', '5/16', '5/17', '5/18', '5/19', '5/20', '5/21'], // x축
				datasets: [{
					label: '탄수화물',
					backgroundColor: '#D7CCC8',
					data: [
						330,
						360,
						320,
						330,
						320,
						330,
						350
					]
				}, {
					label: '단백질',
					backgroundColor: '#88C6FC',
					data: [
						58,
						55,
						57,
						60,
						59,
						63,
						55
					]
				}, {
					label: '지방',
					backgroundColor: '#c9f06f',
					data: [
						54,
						55,
						52,
						53,
						48,
						50,
						54
					]
				}
				]
			},
			options: {
				responsive: true, // 그래프 크기 반응형으로 조정 (dafalut : true)
				tooltips: { // 마우스 호버 시 표시되는 툴팁 옵션
					mode: 'index',
					intersect: false // 마우스가 근처에만 있어도 표현됨
				},
				hover: { // 호버 상호작용 옵션
					mode: 'nearest',
					intersect: true
				},
				animation: {
					duration: 0, // 애니메이션 지속 시간을 0으로 설정
					easing: null // 가속도 함수 제거
				},
				scales: {
					xAxes: [{
						display: true,
						stacked: true
					}],
					yAxes: [{
						display: true,
						stacked: true,
						ticks: {
							callback: function(value, index, values) {
								return value + 'g';
							}
						}
					}]
				}
			}
		});
		
		const lastCarbs = '??'; // 저번 주 탄수화물
    const lastProtein = '??'; // 저번 주 단백질
    const lastFat = '??'; // 저번 주 지방
    const lastSugar = '??'; // 저번 주 당류
    const lastNa = '??'; // 저번 주 나트륨
    const lastCole = '??'; // 저번 주 콜레스테롤
	
		tableCells[0].textContent = lastCarbs;
    tableCells[2].textContent = lastProtein;
    tableCells[4].textContent = lastFat;
    tableCells[6].textContent = lastSugar;
    tableCells[8].textContent = lastNa;
    tableCells[10].textContent = lastCole;
		
	}
};

// select 요소의 변경 이벤트 핸들러 등록
const selectElementTab2 = document.querySelector('.nu_dropdown');
selectElementTab2.addEventListener('change', updateTab2Chart);

// 이번 주 옵션을 선택된 상태로 설정
selectElementTab2.value = 'this_week';

// 초기 로딩 시 그래프와 테이블 업데이트
updateTab2Chart();




