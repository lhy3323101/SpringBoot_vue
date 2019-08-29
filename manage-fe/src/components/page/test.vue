<template>
    <el-button @click="testMethod()">

    </el-button>
</template>

<script>
    import {api} from '@/api/index.js'
    export default {
        name: "test.vue",
        data :function(){
            return{

            }
        },
        methods:{
            testMethod(){
                let param = null;
                api.test(param)
                    .then(data => {
                        console.log(data);
                    })
            }
        }
    }
</script>

<style scoped>

</style>
