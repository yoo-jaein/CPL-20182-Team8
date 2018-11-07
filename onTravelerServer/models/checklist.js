var mongoose = require('mongoose');
var Schema = mongoose.Schema;

var checkListSchema = new Schema({
    customer_id : {type:String, required: true},
    buddy_id : {type:String, required: true},
    state : { type:String, required: true},
    start_time : { type:Date, required: true },
    end_time : {type: Date, required: true},
    location : {type: String, required: true},
    people_number : {type : Number, min : 1, required:true},
    requirement_list : [String],
    suggested_price : {type : Number, min : 0, required: true}
});

checkListSchema.statics.create = function(data){
    var instance = new this(data);

    return instance.save();
};


checkListSchema.statics.findAll = function(){
    return this.find({});
};

module.exports = mongoose.model('checkList', checkListSchema);