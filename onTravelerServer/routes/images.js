var express = require('express');
var router = express.Router();

var multer = require('multer');
var storage = multer.diskStorage({
    destination : function(req, file, cb){
        cb(null, '../uploads/')
    },
    filename : function(req, file, cb){
        cb(null, file.originalname)
    }
});

var upload = multer({dest : "../uploads/"});

/* GET home page. */
router.get('/', function(req, res, next) {
    res.render('uploadTest', { title: 'Upload-Test' });
});


router.post('/upload', upload.single('image'), function(req,res){
    res.send({success : true, 'Uploaded! : ' : req.file  });
});


router.get('/download/:path', function (req, res){
    var path = req.params.path;
    console.log(path);
    res.download('../uploads/' + path, path);
});

module.exports = router;
