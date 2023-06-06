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

	// 현재 그래프 인스턴스를 가져옴
	const chartInstance = Chart.getChart("caloryChart");

	if (selectedOption === 'this_week') {
		if (chartInstance) {
			// 그래프가 이미 존재하는 경우 데이터만 업데이트
			chartInstance.data.labels = ['5/22', '5/23', '5/24', '5/25', '5/26', '5/27', '5/28'];
			chartInstance.data.datasets[0].data = [
				1950,
				2100,
				2040,
				1950,
				2190,
				2160,
				2250
			];
			chartInstance.data.datasets[0].borderColor = '#88C6FC';
			chartInstance.update();
		} else {
			// 그래프가 없는 경우 새로 생성
			new Chart(document.getElementById("caloryChart"), {
				type: 'line',
				data: {
					labels: ['5/22', '5/23', '5/24', '5/25', '5/26', '5/27', '5/28'],
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
						fill: false,
						tension: 0
					}]
				},
				options: {
					plugins: {
						legend: { display: false },
						tooltip: {
							mode: 'index',
							intersect: false,
						},
						interaction: {
							mode: 'nearest',
							intersect: true
						}
					},
					scales: {
						y: {
							display: true,
							ticks: {
								callback: function(value) {
									return value + 'kcal';
								}
							}
						}
					}
				}
			});
		}
	} else if (selectedOption === 'last_week') {
		// 그래프가 이미 존재하는 경우 데이터만 업데이트
		if (chartInstance) {
			chartInstance.data.labels = ['5/15', '5/16', '5/17', '5/18', '5/19', '5/20', '5/21'];
			chartInstance.data.datasets[0].data = [
				1900,
				2000,
				2020,
				1970,
				2150,
				2180,
				2200
			];
			chartInstance.data.datasets[0].borderColor = '#c9f06f';
			chartInstance.update();
		} else {
			// 그래프가 없는 경우 새로 생성
			new Chart(document.getElementById("caloryChart"), {
				type: 'line',
				data: {
					labels: ['5/15', '5/16', '5/17', '5/18', '5/19', '5/20', '5/21'],
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
						fill: false,
						tension: 0
					}]
				},
				options: {
					plugins: {
						legend: { display: false },
						tooltip: {
							mode: 'index',
							intersect: false,
						},
						interaction: {
							mode: 'nearest',
							intersect: true
						}
					},
					scales: {
						y: {
							display: true,
							ticks: {
								callback: function(value) {
									return value + 'kcal';
								}
							}
						}
					}
				}
			});
		}
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

	// 현재 그래프 인스턴스를 가져옴
	const chartInstance = Chart.getChart("nutrientChart");

	// 이번 주
	if (selectedOption === 'this_week') {
		// 그래프가 이미 존재하는 경우 데이터만 업데이트
		if (chartInstance) {
			chartInstance.data.labels = ['5/22', '5/23', '5/24', '5/25', '5/26', '5/27', '5/28'];
			chartInstance.data.datasets[0].data = [
				324,
				350,
				327,
				310,
				330,
				360,
				350
			];
			chartInstance.data.datasets[1].data = [
				55,
				58,
				60,
				57,
				63,
				59,
				55
			];
			chartInstance.data.datasets[2].data = [
				52,
				54,
				55,
				53,
				50,
				48,
				50
			];
			chartInstance.update();
		} else {
			// 그래프가 없는 경우 새로 생성
			new Chart(document.getElementById("nutrientChart"), {
				type: 'bar',
				data: {
					labels: ['5/22', '5/23', '5/24', '5/25', '5/26', '5/27', '5/28'],
					datasets: [{
						label: '탄수화물',
						backgroundColor: '#D7CCC8',
						data: [
							265,
							270,
							270,
							250,
							265,
							255,
							250
						]
					}, {
						label: '단백질',
						backgroundColor: '#88C6FC',
						data: [
							140,
							144,
							145,
							147,
							146,
							148,
							150
						]
					}, {
						label: '지방',
						backgroundColor: '#c9f06f',
						data: [
							48,
							47,
							48,
							46,
							45,
							46,
							44
						]
					}]
				},
				options: {
					plugins: {
						tooltip: {
							mode: 'nearest',
							intersect: false
						}
					},
					interaction: {
						mode: 'nearest',
						intersect: true
					},
					scales: {
						x: {
							display: true,
							stacked: true
						},
						y: {
							display: true,
							stacked: true,
							ticks: {
								callback: function(value) {
									return value + 'g';
								}
							}
						}
					}
				}
			});
		}

		const thisCarbs = '?';
		const thisProtein = '?';
		const thisFat = '?';
		const thisSugar = '?';
		const thisNa = '?';
		const thisCole = '?';

		tableCells[0].textContent = thisCarbs;
		tableCells[2].textContent = thisProtein;
		tableCells[4].textContent = thisFat;
		tableCells[6].textContent = thisSugar;
		tableCells[8].textContent = thisNa;
		tableCells[10].textContent = thisCole;

		// 저번 주
	} else if (selectedOption === 'last_week') {
		// 그래프가 이미 존재하는 경우 데이터만 업데이트
		if (chartInstance) {
			chartInstance.data.labels = ['5/15', '5/16', '5/17', '5/18', '5/19', '5/20', '5/21'];
			chartInstance.data.datasets[0].data = [
				280,
				270,
				275,
				270,
				265,
				270,
				265
			];
			chartInstance.data.datasets[1].data = [
				140,
				138,
				142,
				145,
				143,
				144,
				145
			];
			chartInstance.data.datasets[2].data = [
				54,
				55,
				52,
				53,
				54,
				53,
				52
			];
			chartInstance.update();
		} else {
			// 그래프가 없는 경우 새로 생성
			new Chart(document.getElementById("nutrientChart"), {
				type: 'bar',
				data: {
					labels: ['5/15', '5/16', '5/17', '5/18', '5/19', '5/20', '5/21'],
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
					}]
				},
				options: {
					plugins: {
						tooltip: {
							mode: 'nearest',
							intersect: false
						}
					},
					interaction: {
						mode: 'nearest',
						intersect: true
					},
					scales: {
						x: {
							display: true,
							stacked: true
						},
						y: {
							display: true,
							stacked: true,
							ticks: {
								callback: function(value) {
									return value + 'g';
								}
							}
						}
					}
				}
			});
		}

		const lastCarbs = '??';
		const lastProtein = '??';
		const lastFat = '??';
		const lastSugar = '??';
		const lastNa = '??';
		const lastCole = '??';

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