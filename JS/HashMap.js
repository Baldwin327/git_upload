/*
**第1題JS
*/
const HashMap = function () {
    let obj = {};
    return {
        put: function (k, v) {
            obj[k] = v;
        },
        keys: function () {
            let arr = [];
            for (let tempKey in obj) {
                arr.push(tempKey)
            }
            return (arr);
        },
        contains: function (k) {
            return obj.hasOwnProperty(k);
        },
        get: function (k) {
            return obj[k];
        },
        clear: function () {
            obj = {};
        }
    };
};