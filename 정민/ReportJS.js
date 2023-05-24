const tabList = document.querySelectorAll('.tab_menu .list li');
const contents = document.querySelectorAll('.tab_menu .cont_area .cont')
let activeCont = ''; // 현재 활성화 된 컨텐츠 (기본:#tab1 활성화)

for (var i = 0; i < tabList.length; i++) {
  tabList[i].querySelector('.btn').addEventListener('click', function(e) {
    e.preventDefault();
    for (var j = 0; j < tabList.length; j++) {
      // 나머지 버튼 클래스 제거
      tabList[j].classList.remove('is_on');

      // 나머지 컨텐츠 display:none 처리
      contents[j].style.display = 'none';
    }

    // 버튼 관련 이벤트
    this.parentNode.classList.add('is_on');

    // 버튼 클릭시 컨텐츠 전환
    activeCont = this.getAttribute('href');
    document.querySelector(activeCont).style.display = 'block';
  });
}

// 그래프
new Chart(document.getElementById("weightChart"), {
    type: 'line',
    data: {
        labels: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
        datasets: [{
            label: 'Weight',
            data: [
                65,
                70,
                68,
                65,
                73,
                72,
                75,
                73,
                70,
                68,
                70,
                72
            ],
            fill: false,
            borderColor: "#c9f06f",
            lineTension: 0
        }]
    },
    options: {
        responsive: true, // 컨테이너가 수행 할 때 차트 캔버스의 크기를 조정(dafalut : true)
        maintainAspectRatio: true, // // (width / height) 크기를 조정할 떄 원래 캔버스 종횡비를 유지 (defalut : true)
        tooltips: {
            mode: 'index',
            intersect: false,
        },
        hover: {
            mode: 'nearest',
            intersect: true
        },
        scales: {
            x: {
                display: true,
                scaleLabel: {
                    display: true,
                    labelString: 'Month'
                }
            },
            y: {
                display: true,
                scaleLabel: {
                    display: true,
                }
            }
        }
    }
});

new Chart(document.getElementById("caloryChart"), {
    type: 'line',
    data: {
        labels: ['5/15', '5/16', '5/17', '5/18', '5/19', '5/20', '5/21'],
        datasets: [{
            label: 'Calory',
            data: [
                1950,
                2100,
                2040,
                1950,
                2190,
                2160,
                2250
            ],
            fill: false,
            borderColor: "#88C6FC",
            lineTension: 0
        }]
    },
    options: {
        responsive: true, // 컨테이너가 수행 할 때 차트 캔버스의 크기를 조정(dafalut : true)
        maintainAspectRatio: true, // // (width / height) 크기를 조정할 떄 원래 캔버스 종횡비를 유지 (defalut : true)
        tooltips: {
            mode: 'index',
            intersect: false,
        },
        hover: {
            mode: 'nearest',
            intersect: true
        },
        scales: {
            x: {
                display: true,
                scaleLabel: {
                    display: true,
                    labelString: 'Month'
                }
            },
            y: {
                display: true,
                scaleLabel: {
                    display: true,
                }
            }
        }
    }
});

new Chart(document.getElementById("nutrientChart"), {
     type: 'doughnut',
    data: {
      labels: ['탄수화물', '단백질', '지방', '당류', '나트륨', '콜레스테롤', 'etc'],
      datasets: [{
        data: [40, 30, 10, 10, 5, 3, 2],      // 섭취량, 총급여량 - 섭취량
        backgroundColor: [
          '#FFCCCC',
          '#FFE5CC',
          '#FFFFCC',
          '#CCFFE5',
          '#CCE5FF',
          '#E5CCFF',
          '#FFCCE5'
        ],
        borderWidth: 0,
        scaleBeginAtZero: true,
      }
    ]
  },
});
