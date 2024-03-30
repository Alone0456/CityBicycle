<template>
    <div id="module" style="background-color: #11145b;">
        <Weather></Weather>
        <!-- 第一行 -->
        <div class="module-box">
            <div class="box" v-for="item in listBox" :key="item.id" @click="linkTo(item.id)">
                <div class="list-box">
                    <i class="el-icon-edit"></i>
                    <p>{{ item.title }}</p>
                </div>
            </div>
        </div>
        <!-- 第二行 -->
        <div class="index-content">
            <div class="content-right">
                <el-card class="box-card content" style="height: 490px;background-color: #20266d;">
                    <!-- 地图展示 -->
                    <MyMap></MyMap>
                </el-card>
            </div>
            <div class="content-left">
                <div style="flex:0 1 100%">
                    <el-card class="box-card content">
                        <Chart height="200px" :option="liquidFillOpt"></Chart>
                    </el-card>
                </div>
                <div style="flex:0 1 100%">
                    <el-card class="box-card content">
                        <Chart height="200px" :option="barOption"></Chart>
                    </el-card>
                </div>
            </div>
        </div>
        <!-- 第三行 -->
        <div class="index-content">
            <div style="flex:0 1 40%">
                <el-card class="box-card content">
                    <Chart height="300px" :option="lineOption"></Chart>
                </el-card>
            </div>
            <div style="flex:0 1 60%">
                <el-card class="box-card content">
                    <Chart height="300px" :option="lineBarOption"></Chart>
                </el-card>
            </div>
        </div>
    </div>
</template>

<script>
// import { ChromeFilled, TakeawayBox, LocationFilled, Opportunity, PieChart } from '@element-plus/icons-vue'
import Chart from './components/chart.vue'
import MyMap from './components/myMap.vue'
import Weather from './components/weather.vue'
import { useLiquidFill } from './options/liquidFill.js'
import { useLineBar } from './options/lineBar.js'
import { usePie } from './options/pie.js'
import { useLine } from './options/line.js'
import { apiUserInfo } from '@/api/modules/user.js'

export default {
    components: {
        Chart,
        MyMap,
        Weather
    },
    data() {
        return {
            listBox: [
                { id: 1, title: '车辆管理' },
                { id: 2, title: '订单管理' },
                { id: 3, title: '租借管理' },
                { id: 4, title: '站点管理' }
            ],

        }
    },
    mounted() {
        // apiUserInfo().then(res => {
        //     console.log(res)
        // })
        // this.liquidFillOpt = useLiquidFill(this.liquidFillData)
    },
    computed: {
        liquidFillOpt() {
            return useLiquidFill()
        },
        lineBarOption() {
            return useLineBar()
        },
        barOption() {
            return usePie()
        },
        lineOption() {
            return useLine()
        }
    },
    methods: {
        linkTo(id) {
            if (id == 1) {
                this.$router.push('/bicycle/query')
            } else if (id == 2) {
                this.$router.push('/order/query')
            } else if (id == 3) {
                this.$router.push('/rented/query')
            } else if (id == 4) {
                this.$router.push('/station/manage')
            }
        }
    },
}
</script>

<style scoped>
* {
    padding: 0px;
    margin: 0px;
}

ul,
li {
    list-style-type: none;
    padding: 0;
    margin: 0;
}

a {
    color: #333;
    text-decoration: none;
}

body {
    font-size: 14px;
    background-color: #11145C;
}

body,
html {
    width: 100%;
    height: 100%;
}

#module {
    height: 100%;
}

#app {
    height: 100%;
}

input[type=text] {
    border: none;
}


/* 首页 */
#module .el-container {
    height: 100%;
}

#module .el-container .el-menu-item,
#module .el-container .el-sub-menu .el-sub-menu__title {
    color: #f9f9f9;
}

#module .el-container .el-menu-item:hover,
#module .el-container .el-sub-menu .el-sub-menu__title:hover {
    color: #fff;
    background: #30377d;
}

#module .el-menu-item.is-active,
#module .el-sub-menu.is-active {
    color: #fff;
    background: #30377d;
}

#module .el-container .el-sub-menu.is-active.is-opened,
#module .el-container .el-sub-menu.is-active.is-opened .el-sub-menu__title {
    color: #f9f9f9;
    background: #20266d;
}

#module .el-container .el-header {
    /* border-bottom: 1px solid #f1f1f1; */
    background: #20266d;
    height: 90px;
    padding: 0;
}

#module .el-container .el-header .exit {
    margin-top: 10px;
}

#module .aside {
    background: #20266d;
}

#module .aside .el-menu {
    border-right: solid 1px #20266d;
    background: #20266d;
}

#module .el-submenu__title {
    color: #f9f9f9;
}

/* 面包屑 */
#module .breadcrumb {
    padding: 20px;
    display: inline-block;
}

#module .breadcrumb ul li {
    display: inline-block;
}

#module .breadcrumb ul li span {
    padding: 0 5px;
}

#module .el-carousel__item img {
    color: #fff;
    font-size: 14px;
    opacity: 0.75;
    line-height: 200px;
    margin: 0;
    max-width: 100%;
}

#module .breadcrumb .el-breadcrumb__item .el-breadcrumb__inner,
#module .breadcrumb .el-breadcrumb__item .el-breadcrumb__inner a {
    color: #fff;
}

#module .el-carousel__item:nth-child(2n) {
    background-color: #99a9bf;
}

#module .el-carousel__item:nth-child(2n+1) {
    background-color: #d3dce6;
}

/* tags多页签 */
#module .el-container .el-header .tags {
    padding: 3px 10px;
    background: #20266d;
    border-top: 1px solid #30377d;
}

#module .el-container .el-header .tags span {
    margin: 0 3px;
    cursor: pointer;
}

/* 首页主体部分 */
#module .index-content {
    display: flex;
    width: 100%;
}

#module .index-content .content-left {
    flex: 30%;
}

#module .index-content .content-right {
    flex: 70%;
}

#module .index-content .content {
    margin: 10px;
    background: #11145b;
    min-height: 240px;
}


#module .el-main .el-card {
    border: none;
    background: #20266d;
}

#module .el-main .el-card .el-card__body {
    color: #fff;
}

@media screen and (max-width: 1024px) {
    .index-title li {
        width: 18%;
    }
}

.index-title li:nth-child(1) {
    background: #fe8688;
}

.index-title li:nth-child(2) {
    background: #feba35;
}

.index-title li:nth-child(3) {
    background: #1bc6bd;
}

.index-title li:nth-child(4) {
    background: #ba99ff;
}

.el-pagination {
    /*分页间距 */
    padding: 10px 0;
}

.banner {
    position: relative;
    float: left;
}

.banner .banner-circle {
    position: absolute;
    bottom: 5px;
    left: 0;
    right: 0;
    color: #fff;
}

.banner .banner-circle li {
    display: inline-block;
    background: rgba(0, 0, 0, .3);
    border-radius: 50%;
    padding: 5px;
    margin: 2px;
}

.banner .banner-circle ul {
    text-align: center;
}

.banner .banner-circle .selected {
    background: rgba(0, 0, 0, .8);
}

.banner img {
    max-width: 100%;
    margin: 0;
    padding: 0;
}

.table {
    width: 100%;
    border-collapse: collapse;
    margin: 20px 10px;
    text-align: center;
}

.table tbody {
    background: #fff;
}

.table td,
.table th {
    border: 1px solid #1890ff;
    padding: 10px;
}

.table thead tr {
    background: #1f76b3;
    color: #fff;
}

.module-box {
    width: 95%;
    display: flex;
    /*弹性布局*/
    justify-content: space-between;
    align-items: center;
    height: 200px;
    padding: 30px;
}

.list-box {
    width: 125%;
    margin: 0 10px;
    margin-left: 0;
    height: 120px;
    padding: 20px;
    box-shadow: 0 3px 20px 2px rgb(0 0 0 / 30%);
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    color: #fff;
    border-radius: 8px;
}

.list-box i {
    font-size: 40px;
}

.box {
    flex: 0 1 20%;
    margin: 5px;
    cursor: pointer;
}

.box:hover {
    scale: 1.1;
}

.box:nth-child(1) .list-box {
    background-image: linear-gradient(#2277ce, #0356ab);
}

.box:nth-child(2) .list-box {
    background-image: linear-gradient(#ba5bd8, #9f3fbd);
}

.box:nth-child(3) .list-box {
    background-image: linear-gradient(#ea8052, #cf673a);
}

.box:nth-child(4) .list-box {
    background-image: linear-gradient(#84f395, #71c24a);
}

.box:nth-child(5) .list-box {
    background-image: linear-gradient(#A44A38, #902569);
}

.marginBottom {
    margin-bottom: 10px;
}

#module .el-table {
    --el-table-border-color: #30377d;
    --el-table-border: 1px solid #30377d;
    --el-table-text-color: #fff;
    --el-table-header-text-color: #fff;
    --el-table-row-hover-bg-color: #27339e;
    --el-table-current-row-bg-color: #30377d;
    --el-table-header-bg-color: transparent;
    --el-table-fixed-box-shadow: transparent;
    --el-table-bg-color: transparent;
    --el-table-tr-bg-color: transparent;
    --el-table-expanded-cell-bg-color: transparent;
    /* --el-table-fixed-left-column: inset 10px 0 10px -10px rgba(0, 0, 0, 0.15);
  --el-table-fixed-right-column: inset -10px 0 10px -10px rgba(0, 0, 0, 0.15); */
    /* --el-table-index: var(--el-index-normal); */
}

#module .el-table--striped .el-table__body tr.el-table__row--striped td.el-table__cell {
    background: #27339e;
}

#module .container-wrap {
    min-height: 650px;
}

#module .pagination-wrap {
    margin-top: 20px;
}

#module .pagination-wrap .el-pagination__total,
#module .pagination-wrap .el-pagination__goto,
#module .pagination-wrap .el-pagination__classifier {
    color: #fff
}

.btn-list {
    margin-bottom: 10px;
}
</style>
