Ext.onReady(function() {
    var blocks = Ext.select("div.exampleblock");
    var loc = window.location.href;
    var exbase = "../"
    if (/^http:\/\/(www\.)?geogxt.org\/showcase/.test(loc)) {
        exbase = "http://api.geogxt.org/" + docversion + "/showcase/";
    } else if (/^http:\/\/dev.geogxt.org\/docs/.test(loc)) {
        exbase = "http://dev.geogxt.org/trunk/geogxt/showcase/";
    }
    blocks.each(function(el) {
        el.wrap({
            tag: "a", 
            href: el.first().id.replace(
                /^example-(.*)/, 
                exbase + "$1/"
            ),
            cls: "examplelink",
            target: "_blank"
        });
    });
});