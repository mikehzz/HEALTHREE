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
new Chart(document.getElementById("caloryChart"), {
	type: 'line',
	data: {
		labels: ['5/15', '5/16', '5/17', '5/18', '5/19', '5/20', '5/21'], // x축, 마지막 날짜가 today가 되어야 함 
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

/*
 * 영양소 그래프
 */
new Chart(document.getElementById("nutrientChart"), {
	type: 'bar',
	data: {
		labels: ['5/15', '5/16', '5/17', '5/18', '5/19', '5/20', '5/21'], // x축
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

