
const GoogleStrategy = require('passport-google-oauth20').Strategy;
const LocalStrategy = require('passport-local').Strategy;

module.exports = (passport) => {
    passport.serializeUser(function (user, done) {
        done(null, user);
    });

    passport.deserializeUser(function (obj, done) {
        done(null, obj);
    });

    passport.use(new GoogleStrategy({
            clientID: '651086014912-po5i3t31fununbt9aaf55qqpqct3qgn2.apps.googleusercontent.com',
            clientSecret: 'k4kNlFxhDSUCtJOvXI_xKI4N',
            callbackURL: 'http://localhost:3000/auth/google/callback'
        }, function (accessToken, refreshToken, profile, done) {
            process.nextTick(function () {
                user = profile;
                return done(null, user);
            })
        })
    );
};
