function renderMemberDetails(pageDate, independentPage, footerFunc) {
	const keys = Object.keys(pageDate)
	let arr = []
	for (let i = 0; i < keys.length; i++) {
		const res = pageDate[keys[i]]
		let page = `<div class="w-[2339px] h-[1653px] relative second-page ${
			i === 0 ? 'pt-[86px]' : 'pt-[83px]'
		}">
			${
				i === 0
					? '<div class="text-[28px] leading-[28px] pl-[54px]" style="padding-bottom:23px;">Member Details :-</div>'
					: ''
			}
			<div class="w-[2286px] pl-[54px]">
        <div class="flex flex-nowrap h-[139.5px] text-xl font-bold">
        <div class="w-[95px] border border-black flex justify-center items-center pl-[10px] pt-[2px]">
          Sl. No.
        </div>
        <div
          class="w-[158px] border-none-l border-black border-l-0 flex justify-center items-center pl-[10px]"
        >
          UAN
        </div>
        <div class="border-black border-l-0 flex flex-col">
          <div class="h-[55px] border-none-l flex justify-center items-center pl-[10px] pt-[2px]" >Name as per</div>
          <div class="h-[84.5px] flex">
            <div
              class="h-full w-[164px] border-b-r border-black flex justify-center items-center pl-[10px] pt-[2px]"
            >
              ECR
            </div>
            <div class="h-full w-[139px] text-center border-b-r pl-[10px] pt-[2px]" style="line-height: 26px;padding-top: 4px;">UAN Repository</div>
          </div>
        </div>
        <div class="flex flex-col">
          <div class="h-[55px] pl-[10px] border-none-l border-black border-l-0 border-b-0 flex justify-center items-center pt-[2px]">Wages</div>
          <div class="h-[84.5px] flex">
            <div
              class="h-full w-[111px] border-b-r border-black border-l-0 flex justify-center items-center pl-[10px] pt-[2px]"
            >
              Gross
            </div>
            <div
              class="h-full w-[111px] border-b-r border-black border-l-0 flex justify-center items-center pl-[10px] pt-[2px]"
            >
              EPF
            </div>
            <div
              class="h-full w-[111px] border-b-r border-black border-l-0 flex justify-center items-center pl-[10px] pt-[2px]"
            >
              EPS
            </div>
            <div class="h-full w-[111px] border-b-r border-black border-l-0 flex justify-center items-center pl-[10px] pt-[2px]">EDLI</div>
          </div>
        </div>
        <div class="flex flex-col">
          <div class="h-[55px] border-none-l border-black border-l-0 border-b-0 flex justify-center items-center pl-[10px] pt-[2px]">
            Contribution Remitted
          </div>
          <div class="h-[84.5px] flex">
            <div
              class="h-full w-[139px] border-b-r border-black border-l-0 flex justify-center items-center pl-[10px] pt-[2px]"
            >
              EE
            </div>
            <div
              class="h-full w-[139px] border-b-r border-black border-l-0 flex justify-center items-center pl-[10px] pt-[2px]"
            >
              EPS
            </div>
            <div
              class="h-full w-[140px] border-b-r border-black border-l-0 flex justify-center items-center pl-[10px] pt-[2px]"
            >
              ER
            </div>
            <div class="h-full w-[96.5px] border-b-r border-black border-l-0 text-center flex items-center pl-[10px] pt-[2px]" style="line-height: 26px;">NCP Days</div>
          </div>
        </div>
        <div
          class="w-[139px] border-none-l border-black border-l-0 flex justify-center items-center pl-[10px] pt-[2px]"
        >
          Refunds
        </div>
        <div class="flex flex-col">
          <div class="h-[55px]  border-none-l border-black border-l-0 border-b-0  flex justify-center items-center pl-[10px] pt-[2px]">
            PMRPY / ABRY Benefit
          </div>
          <div class="h-[84.5px] flex">
            <div
              class="h-full w-[139px]  border-b-r border-black border-l-0 text-center flex items-center pl-[10px] pt-[2px]"
              style="line-height: 26px;"
            >
              Pension Share
            </div>
            <div
              class="h-full w-[139px] border-b-r border-black border-l-0 flex text-center items-center px-4 pl-[10px] pt-[2px]"
              style="padding-left: 25px;line-height: 26px;"
            >
              ER PF Share
            </div>
            <div class="h-full w-[153px]  border-b-r border-black border-l-0 flex justify-center items-center pl-[10px] pt-[2px]">
              EE Share
            </div>
          </div>
        </div>
        <div class="w-[144px] border-none-l border-black border-l-0 text-center flex pl-[10px]" style="line-height: 26px; padding-top: 20px;padding-left:10.4px">
          Posting Location of the member
        </div>
      </div>
			<div class="table-content relative" style="z-index: 1;font-weight:350">`

		const temp = res.map((item, index) => {
			return `<div class="flex flex-nowrap text-[20px] " style="height: ${+item.rowHigh + 1}px">
				<div class="w-[95px] flex justify-center items-center border-none-t border-black h-full border-t-0 " style="padding-right:10px">
					${item.SiNo || ''}
				</div>
				<div
					class="w-[158px] flex justify-center items-center border-b-r border-black h-full  border-l-0 border-t-0"
				>
					${item.UAN || ''}
				</div>
				<div class="h-full w-[164px] border-b-r leading-[20px] border-black flex items-center pl-[12px]  border-l-0 border-t-0">
					${item.ECR || ''}
				</div>
				<div class="h-full w-[139px] border-b-r leading-[20px] border-black flex items-center pl-[12px]  border-l-0 border-t-0">
					${item.UANRepository || ''}
				</div>
				<div class="h-full w-[111px] border-b-r border-black flex justify-end px-[12px]  border-l-0 border-t-0">
					${item.Gross || ''}
				</div>
				<div class="h-full w-[111px] border-b-r border-black flex justify-end px-[12px]  border-l-0 border-t-0">
					${item.EPF || ''}
				</div>
				<div class="h-full w-[111px] border-b-r border-black flex justify-end px-[12px]  border-l-0 border-t-0">
					${item.EPS || ''}
				</div>
				<div class="h-full w-[111px] border-b-r border-black flex justify-end px-[12px]  border-l-0 border-t-0">
					${item.EDLI || ''}
				</div>
				<div
					class="h-full w-[139px] border-b-r border-black flex justify-end items-center px-[12px]  border-l-0 border-t-0"
				>
					${item.EE || ''}
				</div>
				<div
					class="h-full w-[139px] border-b-r border-black flex justify-end items-center px-[12px]  border-l-0 border-t-0"
				>
					${item.CREPS || ''}
				</div>
				<div
					class="h-full w-[140px] border-b-r border-black flex justify-end items-center px-[12px]  border-l-0 border-t-0"
				>
					${item.ER || ''}
				</div>
				<div class="h-full w-[96.5px] border-b-r border-black flex justify-end px-[12px]  border-l-0 border-t-0 border-r-0">
					${item.NCPDays || ''}
				</div>
				<div
					class="h-full w-[139px] border-b-r border-black flex items-center justify-end px-[12px] border-t-0"
				>
					${item.Refunds || ''}
				</div>
				<div
					class="h-full w-[139px] border-b-r border-black flex items-center justify-end px-[12px]  border-l-0 border-t-0"
				>
					${item.PensionShare ? item.PensionShare : '-'}
				</div>
				<div
					class="h-full w-[139px] border-b-r border-black flex items-center justify-end px-[12px]  border-l-0 border-t-0"
				>
					${item.ERPFShare ? item.ERPFShare : '-'}
				</div>
				<div
					class="h-full w-[153px] border-b-r border-black flex items-center justify-end px-[12px]  border-l-0 border-t-0"
				>
					${item.EEShare ? item.EEShare : '-'}
				</div>
				<div
					class="h-full w-[144px] border-b-r border-black flex items-center justify-end px-[12px] border-l-0 border-t-0"
				>
					${item.PostingLocationofthemember ? item.PostingLocationofthemember : 'N.A.'}
				</div>
			</div>`
		})
		page +=
			temp.join('') +
			`</div>
      </div>
      ${i === keys.length - 1 && independentPage ? renderRemarks(independentPage) : ''}
      ${footerFunc(i + 2)}
    </div>
    ${
			i === keys.length - 1 && !independentPage
				? renderRemarks(independentPage, footerFunc(i + 3))
				: ''
		}
    `
		arr.push(page)
	}
	document.querySelector('.content').innerHTML = arr.join('')
}

