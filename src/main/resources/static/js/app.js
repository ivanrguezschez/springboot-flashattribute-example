const $ = selector => document.querySelector(selector);
const $$ = selector => document.querySelectorAll(selector);

function cleanDivMessageSuccess() {
	cleanElement($('#divMessageSuccess'));
}
function cleanElement(el) {
	if (el) {
		el.remove();
	}
}