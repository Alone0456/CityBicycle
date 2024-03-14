<template>
    <div>
        <div id="container">
        </div>
    </div>
</template>

<script>
import AMapLoader from '@amap/amap-jsapi-loader'
import apiKey from '@/utils/config'

export default {
    mounted() {
        this.initMap()
    },
    methods: {
        initMap() {
            AMapLoader.load({
                key: apiKey,
                plugins: ["AMap.CitySearch", "AMap.Weather"],
            }).then(AMap => {
                AMap.plugin('AMap.Weather', () => {
                    var weather = new AMap.Weather();
                    weather.getLive('广州市', (err, data) => {
                        console.log(data, err);
                    });
                    weather.getForecast('广州市', (err, data) => {
                        console.log(data, err);
                    });
                });
            }).catch(err => {
                console.log(err)
            })
        }
    },
    destroyed() {
        if (window.AMap) {
            window.AMap.destroy();
        }
    }
}
</script>

<style scoped></style>
