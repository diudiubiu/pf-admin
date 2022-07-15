if (/macintosh|mac os x/i.test(navigator.userAgent)) {
  window.system = 'mac'
} else {
	window.system = 'window'
}
function render() {
	const {
		pageDate,
		employeeData,
		independentPage,
		totalEPFContributionRemitted,
		totalEPSContributionRemitted,
		totalEPFEPSContributionRemitted,
		pageCount,
		totalNumber,
	} = JSON.parse(localStorage.getItem('employeeJson'))
	const { wageMonth, uploadedDateTime } = employeeData
	let totalPage = pageCount
	if (independentPage) {
		totalPage += 1
	} else {
		totalPage += 2
	}
	const footerFunc = renderFooter(wageMonth, uploadedDateTime, totalPage)
	renderEMployee(
		employeeData,
		totalEPFContributionRemitted,
		totalEPSContributionRemitted,
		totalEPFEPSContributionRemitted,
		totalNumber,
		footerFunc
	)
	renderMemberDetails(pageDate, independentPage, footerFunc)
}
render()
function previewAndDown() {
	window.print()
}

