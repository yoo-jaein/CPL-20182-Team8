module.exports = () => {
    return{
        get_format : function(s, data, err){
            return { success : s, size : data.length, datas : data, error : err};
        },
        post_format : function(s, data, err){
            return { success : s, size : data.length, datas :data, error : err};
        },
        put_format : function(s, data, err){
            return { success : s, size : data.length, datas : data, error : err};
        },
        delete_format : function(s, err){
            return {success: s, error : err};
        },
        auth_format : function(s, isNew, data, err){
            return { success : s, new : isNew, user : data, error : err};
        }
    };
};