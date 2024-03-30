import request from '@/utils/request'
import { parseStrEmpty } from "@/utils/ruoyi";

// 查询租借列表
export function listQuery(query) {
    return request({
        url: '/service/query',
        method: 'post',
        data: {
            "page": query.page,
            "size": query.size,
            // "rented_id": query.rented_id,
            // "bicycle_id": query.bicycle_id,
            // "rented_station_id": query.rented_station_id,
            // "rented_user_id": query.rented_user_id,
            // "return_station_id": query.return_station_id
        }
    })
}

export function queryByRentedId(query) {
    return request({
        url: '/service/query',
        method: 'post',
        data: {
            "page": query.page,
            "size": query.size,
            "rented_id": query.rented_id,
        }
    })
}
export function queryByBicycleId(query) {
    return request({
        url: '/service/query',
        method: 'post',
        data: {
            "page": query.page,
            "size": query.size,
            "bicycle_id": query.bicycle_id
        }
    })
}
export function queryByUserId(query) {
    return request({
        url: '/service/query',
        method: 'post',
        data: {
            "page": query.page,
            "size": query.size,
            "rented_user_id": query.rented_user_id
        }
    })
}
export function queryByRentedStationId(query) {
    return request({
        url: '/service/query',
        method: 'post',
        data: {
            "page": query.page,
            "size": query.size,
            "rented_station_id": query.rented_station_id,
        }
    })
}
export function queryByReturnStationId(query) {
    return request({
        url: '/service/query',
        method: 'post',
        data: {
            "page": query.page,
            "size": query.size,
            "return_station_id": query.return_station_id
        }
    })
}

// 查询租借记录
export function recordQuery(query) {
    return request({
        url: '/service/user/query',
        method: 'get',
        params: {
            "page": query.page,
            "size": query.size
        }
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
