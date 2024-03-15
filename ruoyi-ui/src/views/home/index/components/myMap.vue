<template>
    <div ref="mymap" style="height:400px"></div>
</template>

<script>
import * as echarts from 'echarts'
import chinaMap from '@/assets/json/china.json'
import { useMyMap } from '../options/mymap.js'

export default {
    mounted() {
        this.initChart()
    },
    methods: {
        initChart() {
            let mychart = echarts.init(this.$refs.mymap) // 实例化
            echarts.registerMap('china', chinaMap) // 注册地图
            mychart.setOption(useMyMap())
            // 响应式
            window.addEventListener('resize', () => {
                mychart.resize();
            })
        }
    }
}
</script>
