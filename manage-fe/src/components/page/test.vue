<template>
    <div>
        <el-button @click="testMethod()">
            测试数据
        </el-button>
        <div style="font-size: 20px">
            {{this.testText}}
        </div>
    </div>

</template>

<script>
    import {api} from '@/api/index.js'
    export default {
        name: "test.vue",
        data :function(){
            return{
                testText:'',
            }
        },
        methods:{
            testMethod(){
                let param = null;
                api.test(param)
                    .then(data => {
                        console.log(data);
                        this.testText = data.data.content;
                        console.log(this.testText);
                    })
            }
        }
    }
</script>

<style scoped>

</style>
