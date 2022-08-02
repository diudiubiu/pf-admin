function renderFooter(wageMonth, uploadedDateTime, totalPage) {
	return function (curr) {
		return `<div class="absolute bottom-[79px] right-[268px] text-[25px] ">${curr} / ${totalPage}</div>
    <div class="absolute bottom-[79px] text-[25px]" style="padding:0;${
			curr == 1 ? 'padding-left:12px' : 'padding-left:65px'
		}">
      DSNHP2111338000 / ${wageMonth} / ${uploadedDateTime}
    </div>`
	}
}

