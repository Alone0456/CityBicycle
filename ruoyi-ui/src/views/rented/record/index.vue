<template>
    <div class="app-container">
        <el-row :gutter="20">
            <!--部门数据-->
            <!-- <el-col :span="4" :xs="24">
                <div class="head-container">
                    <el-input v-model="deptName" placeholder="请输入部门名称" clearable size="small"
                        prefix-icon="el-icon-search" style="margin-bottom: 20px" />
                </div>
                <div class="head-container">
                    <el-tree :data="deptOptions" :props="defaultProps" :expand-on-click-node="false"
                        :filter-node-method="filterNode" ref="tree" node-key="id" default-expand-all highlight-current
                        @node-click="handleNodeClick" />
                </div>
            </el-col> -->
            <!--用户数据-->
            <el-col :span="24" :xs="24">


                <el-row :gutter="10" class="mb8">

                    <el-col :span="1.5">
                        <el-button type="info" plain icon="el-icon-upload2" size="mini" @click="handleImport"
                            v-hasPermi="['system:user:import']">导入</el-button>
                    </el-col>
                    <el-col :span="1.5">
                        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
                            v-hasPermi="['system:user:export']">导出</el-button>
                    </el-col>
                    <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"
                        :columns="columns"></right-toolbar>
                </el-row>

                <el-table v-loading="loading" :data="userList" @selection-change="handleSelectionChange">
                    <el-table-column type="selection" width="50" align="center" />
                    <el-table-column label="租借编号" align="center" width="120" key="rentedId" prop="rentedId"
                        v-if="columns[0].visible" />
                    <el-table-column label="车辆id" align="center" width="120" key="bicycleId" prop="bicycleId"
                        v-if="columns[1].visible" :show-overflow-tooltip="true" />

                    <el-table-column label="租借站点id" align="center" width="120" key="rentedStationId"
                        prop="rentedStationId" v-if="columns[2].visible" :show-overflow-tooltip="true" />
                    <el-table-column label="归还站点id" align="center" width="120" key="returnStationId"
                        prop="returnStationId" v-if="columns[2].visible" :show-overflow-tooltip="true" />

                    <el-table-column label="租借站点名称" align="center" key="rentedStationName" prop="rentedStationName"
                        v-if="columns[2].visible" :show-overflow-tooltip="true" width="220" />
                    <el-table-column label="归还站点名称" align="center" key="returnStationName" prop="returnStationName"
                        v-if="columns[2].visible" :show-overflow-tooltip="true" width="220" />

                    <el-table-column label="租借时间" align="center" prop="rentedTime" v-if="columns[6].visible"
                        width="auto">
                        <template slot-scope="scope">
                            <span>{{ parseTime(scope.row.rentedTime) }}</span>
                        </template>
                    </el-table-column>
                    <el-table-column label="归还时间" align="center" prop="returnTime" v-if="columns[6].visible"
                        width="auto">
                        <template slot-scope="scope">
                            <span>{{ parseTime(scope.row.returnTime) }}</span>
                        </template>
                    </el-table-column>

                    <el-table-column label="操作" align="center" width="160" class-name="small-padding fixed-width">
                        <template slot-scope="scope">
                            <el-button size="mini" type="text" icon="el-icon-edit" @click="handlePostDamage(scope.row)"
                                v-hasPermi="['system:user:edit']">报修</el-button>
                            <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
                                v-hasPermi="['system:user:remove']">删除</el-button>
                        </template>
                    </el-table-column>


                </el-table>

                <pagination v-show="total > 0" :total="total" :page.sync="queryParams.page"
                    :limit.sync="queryParams.size" @pagination="getList" />
            </el-col>
        </el-row>

        <!-- 添加或修改用户配置对话框 -->
        <el-dialog :title="title" :visible.sync="open" width="600px" append-to-body>
            <el-form ref="form" :model="form" :rules="rules" label-width="80px">
                <el-row>
                    <el-col :span="12">
                        <el-form-item label="用户昵称" prop="nickName">
                            <el-input v-model="form.nickName" placeholder="请输入用户昵称" maxlength="30" />
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="归属部门" prop="deptId">
                            <treeselect v-model="form.deptId" :options="deptOptions" :show-count="true"
                                placeholder="请选择归属部门" />
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="12">
                        <el-form-item label="手机号码" prop="phonenumber">
                            <el-input v-model="form.phonenumber" placeholder="请输入手机号码" maxlength="11" />
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="邮箱" prop="email">
                            <el-input v-model="form.email" placeholder="请输入邮箱" maxlength="50" />
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="12">
                        <el-form-item v-if="form.userId == undefined" label="用户名称" prop="userName">
                            <el-input v-model="form.userName" placeholder="请输入用户名称" maxlength="30" />
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item v-if="form.userId == undefined" label="用户密码" prop="password">
                            <el-input v-model="form.password" placeholder="请输入用户密码" type="password" maxlength="20"
                                show-password />
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="12">
                        <el-form-item label="用户性别">
                            <el-select v-model="form.sex" placeholder="请选择性别">
                                <el-option v-for="dict in dict.type.sys_user_sex" :key="dict.value" :label="dict.label"
                                    :value="dict.value"></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="状态">
                            <el-radio-group v-model="form.status">
                                <el-radio v-for="dict in dict.type.sys_normal_disable" :key="dict.value"
                                    :label="dict.value">{{
            dict.label }}</el-radio>
                            </el-radio-group>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="12">
                        <el-form-item label="岗位">
                            <el-select v-model="form.postIds" multiple placeholder="请选择岗位">
                                <el-option v-for="item in postOptions" :key="item.postId" :label="item.postName"
                                    :value="item.postId" :disabled="item.status == 1"></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="角色">
                            <el-select v-model="form.roleIds" multiple placeholder="请选择角色">
                                <el-option v-for="item in roleOptions" :key="item.roleId" :label="item.roleName"
                                    :value="item.roleId" :disabled="item.status == 1"></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="24">
                        <el-form-item label="备注">
                            <el-input v-model="form.remark" type="textarea" placeholder="请输入内容"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button type="primary" @click="submitForm">确 定</el-button>
                <el-button @click="cancel">取 消</el-button>
            </div>
        </el-dialog>

        <!-- 用户导入对话框 -->
        <el-dialog :title="upload.title" :visible.sync="upload.open" width="400px" append-to-body>
            <el-upload ref="upload" :limit="1" accept=".xlsx, .xls" :headers="upload.headers"
                :action="upload.url + '?updateSupport=' + upload.updateSupport" :disabled="upload.isUploading"
                :on-progress="handleFileUploadProgress" :on-success="handleFileSuccess" :auto-upload="false" drag>
                <i class="el-icon-upload"></i>
                <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
                <div class="el-upload__tip text-center" slot="tip">
                    <div class="el-upload__tip" slot="tip">
                        <el-checkbox v-model="upload.updateSupport" /> 是否更新已经存在的用户数据
                    </div>
                    <span>仅允许导入xls、xlsx格式文件。</span>
                    <el-link type="primary" :underline="false" style="font-size:12px;vertical-align: baseline;"
                        @click="importTemplate">下载模板</el-link>
                </div>
            </el-upload>
            <div slot="footer" class="dialog-footer">
                <el-button type="primary" @click="submitFileForm">确 定</el-button>
                <el-button @click="upload.open = false">取 消</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
import { delUser, addUser, updateUser, resetUserPwd, changeUserStatus, deptTreeSelect } from "@/api/system/user";
import { recordQuery, postDamage } from "@/api/rent/query";
import { getToken } from "@/utils/auth";
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";

export default {
    name: "User",
    dicts: ['sys_normal_disable', 'sys_user_sex'],
    components: { Treeselect },
    data() {
        return {
            // 遮罩层
            loading: true,
            // 选中数组
            ids: [],
            // 非单个禁用
            single: true,
            // 非多个禁用
            multiple: true,
            // 显示搜索条件
            showSearch: true,
            // 总条数
            total: 0,
            // 用户表格数据
            userList: null,
            // 弹出层标题
            title: "",
            // 部门树选项
            deptOptions: undefined,
            // 是否显示弹出层
            open: false,
            // 部门名称
            deptName: undefined,
            // 默认密码
            initPassword: undefined,
            // 日期范围
            dateRange: [],
            // 岗位选项
            postOptions: [],
            // 角色选项
            roleOptions: [],
            // 表单参数
            form: {},
            defaultProps: {
                children: "children",
                label: "label"
            },
            // 用户导入参数
            upload: {
                // 是否显示弹出层（用户导入）
                open: false,
                // 弹出层标题（用户导入）
                title: "",
                // 是否禁用上传
                isUploading: false,
                // 是否更新已经存在的用户数据
                updateSupport: 0,
                // 设置上传的请求头部
                headers: { Authorization: "Bearer " + getToken() },
                // 上传的地址
                url: process.env.VUE_APP_BASE_API + "/system/user/importData"
            },
            // 查询参数
            queryParams: {
                page: 1,
                size: 10,
                stationId: '',
                orderColumn: 'bicycle_num',
                orderType: 'desc'

            },
            // 列信息
            columns: [
                { key: 0, label: `用户编号`, visible: true },
                { key: 1, label: `用户名称`, visible: true },
                { key: 2, label: `用户昵称`, visible: true },
                { key: 3, label: `部门`, visible: true },
                { key: 4, label: `手机号码`, visible: true },
                { key: 5, label: `状态`, visible: true },
                { key: 6, label: `创建时间`, visible: true }
            ],
            // 表单校验
            rules: {
                userName: [
                    { required: true, message: "用户名称不能为空", trigger: "blur" },
                    { min: 2, max: 20, message: '用户名称长度必须介于 2 和 20 之间', trigger: 'blur' }
                ],
                nickName: [
                    { required: true, message: "用户昵称不能为空", trigger: "blur" }
                ],
                password: [
                    { required: true, message: "用户密码不能为空", trigger: "blur" },
                    { min: 5, max: 20, message: '用户密码长度必须介于 5 和 20 之间', trigger: 'blur' },
                    { pattern: /^[^<>"'|\\]+$/, message: "不能包含非法字符：< > \" ' \\\ |", trigger: "blur" }
                ],
                email: [
                    {
                        type: "email",
                        message: "请输入正确的邮箱地址",
                        trigger: ["blur", "change"]
                    }
                ],
                phonenumber: [
                    {
                        pattern: /^1[3|4|5|6|7|8|9][0-9]\d{8}$/,
                        message: "请输入正确的手机号码",
                        trigger: "blur"
                    }
                ]
            }
        };
    },
    watch: {
        // 根据名称筛选部门树
        deptName(val) {
            this.$refs.tree.filter(val);
        }
    },
    created() {
        this.getList();
        this.getDeptTree();
        this.getConfigKey("sys.user.initPassword").then(response => {
            this.initPassword = response.msg;
        });
    },
    methods: {
        /** 查询用户列表 */
        getList() {
            this.loading = true;
            recordQuery(this.addDateRange(this.queryParams, this.dateRange))
                .then(response => {
                    console.log('bicycle', response);
                    this.userList = response.data;
                    this.total = response.data.length;
                    this.loading = false;
                })
                .catch(error => {
                    console.error('Error fetching user list:', error);
                    this.loading = false;
                    // 可以根据需要进行错误处理，比如显示错误信息给用户
                    this.$message.error('获取用户列表失败，请稍后重试');
                });
        },
        getListByStationId() {

            this.loading = true;
            this.queryParams.page = 1;
            if (this.queryParams.stationId == '') {
                this.getList();
            } else {
                queryByStationId(this.addDateRange(this.queryParams, this.dateRange))
                    .then(response => {
                        console.log('queryByStationId', response);
                        this.userList = [];

                        console.log(this.userList);
                        if (response.data == undefined) {
                            this.total = 0;
                        } else {
                            this.userList.push(response.data);
                            this.total = 1;
                        }
                        this.loading = false;
                    })
                    .catch(error => {
                        console.error('Error fetching user list:', error);
                        this.loading = false;
                        // 可以根据需要进行错误处理，比如显示错误信息给用户
                        this.$message.error('获取用户列表失败，请稍后重试');
                    });
            }
        },
        getListByBicycleId() {

            this.loading = true;
            this.queryParams.page = 1;
            if (this.queryParams.bicycleId == '') {
                this.getList();
            } else {
                queryByBicycleId(this.addDateRange(this.queryParams, this.dateRange))
                    .then(response => {
                        console.log('queryByStationId', response);
                        this.userList = [];

                        console.log(this.userList);
                        if (response.data == undefined) {
                            this.total = 0;
                        } else {
                            this.userList.push(response.data);
                            this.total = 1;
                        }
                        this.loading = false;
                    })
                    .catch(error => {
                        console.error('Error fetching user list:', error);
                        this.loading = false;
                        // 可以根据需要进行错误处理，比如显示错误信息给用户
                        this.$message.error('获取用户列表失败，请稍后重试');
                    });
            }
        },
        getListByStatus() {

            this.loading = true;
            this.queryParams.page = 1;
            queryByStatus(this.addDateRange(this.queryParams, this.dateRange))
                .then(response => {
                    console.log('queryByStatus', response);
                    this.userList = [];
                    console.log(this.userList);
                    if (response.data == undefined) {
                        this.total = 0;
                    } else {
                        this.userList.push(response.data);
                        this.total = 1;
                    }
                    this.loading = false;
                })
                .catch(error => {
                    console.error('Error fetching user list:', error);
                    this.loading = false;
                    // 可以根据需要进行错误处理，比如显示错误信息给用户
                    this.$message.error('获取用户列表失败，请稍后重试');
                });
        },
        /** 报修 */
        handlePostDamage(row) {
            this.reset();
            const userId = row.userId || this.ids;
            postDamage(row.bicycleId).then(response => {

                if (response.code === 200) {
                    this.$message.success('已成功报修');
                }

                this.form = response.data;
                this.postOptions = response.posts;
                this.roleOptions = response.roles;
                this.$set(this.form, "postIds", response.postIds);
                this.$set(this.form, "roleIds", response.roleIds);
                this.open = true;
                this.title = "修改用户";
                this.form.password = "";
            });
        },


        /** 查询部门下拉树结构 */
        getDeptTree() {
            deptTreeSelect().then(response => {
                this.deptOptions = response.data;
            });
        },
        // 筛选节点
        filterNode(value, data) {
            if (!value) return true;
            return data.label.indexOf(value) !== -1;
        },
        // 节点单击事件
        handleNodeClick(data) {
            this.queryParams.deptId = data.id;
            this.handleQuery();
        },
        // 用户状态修改
        handleStatusChange(row) {
            let text = row.status === "0" ? "启用" : "停用";
            this.$modal.confirm('确认要"' + text + '""' + row.userName + '"用户吗？').then(function () {
                return changeUserStatus(row.userId, row.status);
            }).then(() => {
                this.$modal.msgSuccess(text + "成功");
            }).catch(function () {
                row.status = row.status === "0" ? "1" : "0";
            });
        },
        // 取消按钮
        cancel() {
            this.open = false;
            this.reset();
        },
        // 表单重置
        reset() {
            this.form = {
                userId: undefined,
                deptId: undefined,
                userName: undefined,
                nickName: undefined,
                password: undefined,
                phonenumber: undefined,
                email: undefined,
                sex: undefined,
                status: "0",
                remark: undefined,
                postIds: [],
                roleIds: []
            };
            this.resetForm("form");
        },
        /** 搜索按钮操作 */
        handleQuery() {
            this.queryParams.pageNum = 1;
            this.getList();
        },
        /** 重置按钮操作 */
        resetQuery() {
            this.dateRange = [];
            this.resetForm("queryForm");
            this.queryParams.deptId = undefined;
            this.$refs.tree.setCurrentKey(null);
            this.handleQuery();
        },
        // 多选框选中数据
        handleSelectionChange(selection) {
            this.ids = selection.map(item => item.userId);
            this.single = selection.length != 1;
            this.multiple = !selection.length;
        },
        // 更多操作触发
        handleCommand(command, row) {
            switch (command) {
                case "handleResetPwd":
                    this.handleResetPwd(row);
                    break;
                case "handleAuthRole":
                    this.handleAuthRole(row);
                    break;
                default:
                    break;
            }
        },
        /** 新增按钮操作 */
        handleAdd() {
            this.reset();
            queryByStationId().then(response => {
                this.postOptions = response.posts;
                this.roleOptions = response.roles;
                this.open = true;
                this.title = "添加用户";
                this.form.password = this.initPassword;
            });
        },
        /** 修改按钮操作 */
        handleUpdate(row) {
            this.reset();
            const userId = row.userId || this.ids;
            queryByStationId(stationId).then(response => {
                this.form = response.data;
                this.postOptions = response.posts;
                this.roleOptions = response.roles;
                this.$set(this.form, "postIds", response.postIds);
                this.$set(this.form, "roleIds", response.roleIds);
                this.open = true;
                this.title = "修改用户";
                this.form.password = "";
            });
        },
        /** 重置密码按钮操作 */
        handleResetPwd(row) {
            this.$prompt('请输入"' + row.userName + '"的新密码', "提示", {
                confirmButtonText: "确定",
                cancelButtonText: "取消",
                closeOnClickModal: false,
                inputPattern: /^.{5,20}$/,
                inputErrorMessage: "用户密码长度必须介于 5 和 20 之间",
                inputValidator: (value) => {
                    if (/<|>|"|'|\||\\/.test(value)) {
                        return "不能包含非法字符：< > \" ' \\\ |"
                    }
                },
            }).then(({ value }) => {
                resetUserPwd(row.userId, value).then(response => {
                    this.$modal.msgSuccess("修改成功，新密码是：" + value);
                });
            }).catch(() => { });
        },
        /** 分配角色操作 */
        handleAuthRole: function (row) {
            const userId = row.userId;
            this.$router.push("/system/user-auth/role/" + userId);
        },
        /** 提交按钮 */
        submitForm: function () {
            this.$refs["form"].validate(valid => {
                if (valid) {
                    if (this.form.userId != undefined) {
                        updateUser(this.form).then(response => {
                            this.$modal.msgSuccess("修改成功");
                            this.open = false;
                            this.getList();
                        });
                    } else {
                        addUser(this.form).then(response => {
                            this.$modal.msgSuccess("新增成功");
                            this.open = false;
                            this.getList();
                        });
                    }
                }
            });
        },
        /** 删除按钮操作 */
        handleDelete(row) {
            const userIds = row.userId || this.ids;
            this.$modal.confirm('是否确认删除用户编号为"' + userIds + '"的数据项？').then(function () {
                return delUser(userIds);
            }).then(() => {
                this.getList();
                this.$modal.msgSuccess("删除成功");
            }).catch(() => { });
        },
        /** 导出按钮操作 */
        handleExport() {
            this.download('system/user/export', {
                ...this.queryParams
            }, `user_${new Date().getTime()}.xlsx`)
        },
        /** 导入按钮操作 */
        handleImport() {
            this.upload.title = "用户导入";
            this.upload.open = true;
        },
        /** 下载模板操作 */
        importTemplate() {
            this.download('system/user/importTemplate', {
            }, `user_template_${new Date().getTime()}.xlsx`)
        },
        // 文件上传中处理
        handleFileUploadProgress(event, file, fileList) {
            this.upload.isUploading = true;
        },
        // 文件上传成功处理
        handleFileSuccess(response, file, fileList) {
            this.upload.open = false;
            this.upload.isUploading = false;
            this.$refs.upload.clearFiles();
            this.$alert("<div style='overflow: auto;overflow-x: hidden;max-height: 70vh;padding: 10px 20px 0;'>" + response.msg + "</div>", "导入结果", { dangerouslyUseHTMLString: true });
            this.getList();
        },
        // 提交上传文件
        submitFileForm() {
            this.$refs.upload.submit();
        }
    }
};
</script>