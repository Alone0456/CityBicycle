import request from '@/utils/request'
import { parseStrEmpty } from "@/utils/ruoyi";

// 查询车辆列表
export function listQuery(query) {
    return request({
        url: '/bicycle/all',
        method: 'get',
        params: {
            "page": query.page,
            "size": query.size
        }
    })
}

// 通过站点id查询车辆列表
export function queryByStationId(stationId) {
    return request({
        url: '/bicycle/' + parseStrEmpty(stationId),
        method: 'get'
    })
}
// 通过状态查询车辆列表
export function queryByStatus(query) {
    return request({
        url: '/bicycle/status',
        method: 'get',
        params: query.status
    })
}
// 通过车辆id查询车辆列表
export function queryByBicycleId(query) {
    return request({
        url: '/bicycle/' + parseStrEmpty(query.bicycleId),
        method: 'get'
    })
}

// 车辆-损坏上报
export function postDamage(bicycleId) {
    return request({
        url: '/damage/record',
        method: 'get',
        params: { "bicycleId": bicycleId }
    })
}

// 车辆-增加车辆
export function addBicycle(bicycle) {
    return request({
        url: '/bicycle/save',
        method: 'post',
        data: { "bicycle": 'bicycle' }
    })
}

// 车辆-损坏登记
export function RecordDamage(bicycleId, details) {
    return request({
        url: '/damage/repair',
        method: 'post',
        data: { "bicycleId": bicycleId, "details": details }
    })
}



// 新增用户
export function addUser(data) {
    return request({
        url: '/system/user',
        method: 'post',
        data: data
    })
}

// 修改用户
export function updateUser(data) {
    return request({
        url: '/system/user',
        method: 'put',
        data: data
    })
}

// 删除用户
export function delUser(userId) {
    return request({
        url: '/system/user/' + userId,
        method: 'delete'
    })
}

// 用户密码重置
export function resetUserPwd(userId, password) {
    const data = {
        userId,
        password
    }
    return request({
        url: '/system/user/resetPwd',
        method: 'put',
        data: data
    })
}

// 用户状态修改
export function changeUserStatus(userId, status) {
    const data = {
        userId,
        status
    }
    return request({
        url: '/system/user/changeStatus',
        method: 'put',
        data: data
    })
}

// 查询用户个人信息
export function getUserProfile() {
    return request({
        url: '/system/user/profile',
        method: 'get'
    })
}

// 修改用户个人信息
export function updateUserProfile(data) {
    return request({
        url: '/system/user/profile',
        method: 'put',
        data: data
    })
}

// 用户密码重置
export function updateUserPwd(oldPassword, newPassword) {
    const data = {
        oldPassword,
        newPassword
    }
    return request({
        url: '/system/user/profile/updatePwd',
        method: 'put',
        params: data
    })
}

// 用户头像上传
export function uploadAvatar(data) {
    return request({
        url: '/system/user/profile/avatar',
        method: 'post',
        data: data
    })
}

// 查询授权角色
export function getAuthRole(userId) {
    return request({
        url: '/system/user/authRole/' + userId,
        method: 'get'
    })
}

// 保存授权角色
export function updateAuthRole(data) {
    return request({
        url: '/system/user/authRole',
        method: 'put',
        params: data
    })
}

// 查询部门下拉树结构
export function deptTreeSelect() {
    return request({
        url: '/system/user/deptTree',
        method: 'get'
    })
}
