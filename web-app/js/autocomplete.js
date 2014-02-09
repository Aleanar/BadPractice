/**
 * Created by Thomas on 15/01/14.
 */

$(document).ready(function () {
    $("#tag-name-auto").tokenInput(tagautocompleteurl, {
        theme: "facebook"
    });
});

/*
$(document).ready(function() {
    $("#tagnameauto").autocomplete({
        source: function(request, response){
            $.ajax({
                url: "/BadPractice/tag/getTagsWithName",
                data: request,
                success: function(data){
                    response(data);
                },
                error: function(){
                    $.jGrowl("Unable to retrieve Companies", {
                        theme: 'ui-state-error ui-corner-all'
                    });
                }
            });
        },
        minLength: 1,
        select: function(event, ui) {
            $("#tag\\.id").val(ui.item.id);
        }
    });
});*/