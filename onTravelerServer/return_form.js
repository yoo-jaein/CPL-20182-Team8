module.exports = () => {
    return{
        get_format : function(s, data, err){
            return { success : s, size : data.length, datas : data, error : err, new : 0};
        },
        post_format : function(s, data, err){
            return { success : s, size : data.length, datas :data, error : err, new : 0};
        },
        put_format : function(s, data, err){
            return { success : s, size : data.length, datas : data, error : err, new : 0};
        },
        delete_format : function(s, err){
            return {success: s, size : 0, datas : "", error : err, new : 0};
        },
        auth_format : function(s, isNew, data, err){
            return { success : s, size : data.length, datas : data, error : err, new : isNew};
        }
    };
};