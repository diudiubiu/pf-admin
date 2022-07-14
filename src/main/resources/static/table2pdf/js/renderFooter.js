function renderFooter(wageMonth, uploadedDateTime, totalPage) {
	return function (curr) {
		return `<div class="absolute bottom-[89px] right-[266px] text-[26px] leading-[26px]">${curr} / ${totalPage}</div>
    <div class="absolute bottom-[89px] pl-[12px] text-[26px] leading-[26px]">
      MRNOI2513599000 / ${wageMonth} / ${uploadedDateTime}
    </div>`
	}
}

