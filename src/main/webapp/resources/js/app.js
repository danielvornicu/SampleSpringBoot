/**
 * Created by d.vornicu on 11/02/2019.
 */

/**
 * Affiche ou masque les erreurs suivant l'action de l'utilisateur
 */
function showHideError() {
    if ($("div.errorContent").is(":visible")) {
        $("img.hideError").hide();
        $("img.showError").show();
    } else {
        $("div.errorHeader").removeClass("ui-corner-bottom");
        $("div.errorHeader").addClass("no-bottom-border");
        $("img.showError").hide();
        $("img.hideError").show();
    }

    $("div.errorContent").toggle( "blind", [], 500, function() {
        if (!$("div.errorContent").is(":visible")) {
            $("div.errorHeader").addClass("ui-corner-bottom");
            $("div.errorHeader").removeClass("no-bottom-border");
        }
    });
}