var mongoose = require('mongoose');
var Schema = mongoose.Schema;

var customerSchema = new Schema({
    customer_id : {type:String, required : true , unique: true},
    pw : { type : String, required : true , trim : true},
    name : { type : String, required : true },
    nickname : { type: String, default:'name', required : true},
    favorite_buddy_id_list : [ String ],
    favorite_feed_item_id_list : [ Schema.Types.ObjectId ],
});

customerSchema.statics.create = function(data){
    var instance = new this(data);

    return instance.save();
};


customerSchema.statics.findAll = function(){
    return this.find({});
};

module.exports = mongoose.model('customer', customerSchema);