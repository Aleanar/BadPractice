if (typeof jQuery !== 'undefined') {
	(function($) {
		$('#spinner').ajaxStart(function() {
			$(this).fadeIn();
		}).ajaxStop(function() {
			$(this).fadeOut();
		});
	})(jQuery);
}

$(document).ready(function() {
    $('#post-editor').summernote();
    $('#comment-editor').summernote({
        toolbar: [
            ['style', ['bold', 'italic', 'underline', 'clear']]
        ]
    });
});