/**
 * Main App Java Script
 */
$('document').ready(function(){

    //Dialog modal de confirmation de suppression-->
    $('.table #boutonSupprimer').on('click',function(event) {
        event.preventDefault();
        var href = $(this).attr('href');
        $('#deleteModal #delRef').attr('href', href);
        $('#deleteModal').modal();
    });
});

document.addEventListener('DOMContentLoaded', function () {
    var $closeIcons = Array.prototype.slice.call(document.querySelectorAll('.close'), 0);

    if ($closeIcons.length > 0) {
        $closeIcons.forEach(function($el) {
            console.log("adding event listener", $el);

            $el.addEventListener('click', function() {
                var target = $el.dataset.target,
                    $target = document.getElementById(target);
                $target.remove();
            });
        });
    }

});