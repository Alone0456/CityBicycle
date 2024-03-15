<template>
    <div ref="chart" :style="style"></div>
</template>

<script>
import * as echarts from 'echarts'

export default {
    props: {
        width: {
            type: String,
            default: '100%'
        },
        height: {
            type: String,
            default: '100%'
        },
        option: {
            type: Object,
            default: () => ({
                title: {
                    text: 'ECharts 入门示例',
                    textStyle: {
                        color: '#fff',
                    },
                },
                textStyle: {
                    color: '#fff',
                },
                tooltip: {},
                legend: {
                    data: ['销量'],
                    textStyle: {
                        color: '#fff',
                    },
                },
                xAxis: {
                    data: ['衬衫', '羊毛衫', '雪纺衫', '裤子', '高跟鞋', '袜子']
                },
                yAxis: {},
                series: [{
                    name: '销量',
                    type: 'bar',
                    data: [5, 20, 36, 10, 10, 20]
                }]
            })
        }
    },
    data() {
        return {
            chart: null,
            style: {
                width: this.width,
                height: this.height
            }
        }
    },
    mounted() {
        this.initChart()
    },
    methods: {
        initChart() {
            this.chart = echarts.init(this.$refs.chart)
            this.chart.setOption(this.option)
            window.addEventListener('resize', () => {
                this.chart.resize()
            })
        }
    }
}
</script>
