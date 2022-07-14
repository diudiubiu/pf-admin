function renderMemberDetails(pageDate, independentPage, footerFunc) {
	const keys = Object.keys(pageDate)
	let arr = []
	for (let i = 0; i < keys.length; i++) {
		const res = pageDate[keys[i]]
		let page = `<div class="w-[2339px] h-[1653px] relative pl-[55px] ${
			i === 0 ? 'pt-[89px]' : 'pt-[83px]'
		}">
			${i === 0 ? '<div class="pb-[29px] text-[28px] leading-[28px]">Member Details :-</div>' : ''}
			<div class="w-[2284px]">
        <div class="flex flex-nowrap h-[140px] text-xl font-bold">
        <div class="w-[96px] border border-black flex justify-center items-center pl-[10px]">
          Sl. No.
        </div>
        <div
          class="w-[159px] border border-black -ml-px flex justify-center items-center pl-[10px]"
        >
          UAN
        </div>
        <div class="w-[304px] border border-black -ml-px flex flex-col">
          <div class="h-[54px] flex justify-center items-center pl-[10px]">Name as per</div>
          <div class="h-[84px] border-t border-black flex">
            <div
              class="h-full w-[164px] border-r border-black flex justify-center items-center pl-[10px]"
            >
              ECR
            </div>
            <div class="h-full w-[138px] text-center -ml-px pl-[10px]">UAN Repository</div>
          </div>
        </div>
        <div class="w-[445px] border border-black -ml-px flex flex-col">
          <div class="h-[54px] flex justify-center items-center">Wages</div>
          <div class="h-[84px] border-t border-black flex">
            <div
              class="h-full w-[111px] border-r border-black flex justify-center items-center pl-[10px]"
            >
              Gross
            </div>
            <div
              class="h-full w-[111px] border-r border-black flex justify-center items-center pl-[10px]"
            >
              EPF
            </div>
            <div
              class="h-full w-[111px] border-r border-black flex justify-center items-center pl-[10px]"
            >
              EPS
            </div>
            <div class="h-full w-[110px] flex justify-center items-center pl-[10px]">EDLI</div>
          </div>
        </div>
        <div class="w-[515px] border border-black -ml-px flex flex-col">
          <div class="h-[54px] flex justify-center items-center pl-[10px]">
            Contribution Remitted
          </div>
          <div class="h-[84px] border-t border-black flex">
            <div
              class="h-full w-[139px] border-r border-black flex justify-center items-center pl-[10px]"
            >
              EE
            </div>
            <div
              class="h-full w-[139px] border-r border-black flex justify-center items-center pl-[10px]"
            >
              EPS
            </div>
            <div
              class="h-full w-[139px] border-r border-black flex justify-center items-center pl-[10px]"
            >
              ER
            </div>
            <div class="h-full w-[96px] text-center flex items-center pl-[10px]">NCP Days</div>
          </div>
        </div>
        <div
          class="w-[140px] border border-black -ml-px flex justify-center items-center pl-[10px]"
        >
          Refunds
        </div>
        <div class="w-[431px] border border-black -ml-px flex flex-col">
          <div class="h-[54px] flex justify-center items-center pl-[10px]">
            PMRPY / ABRY Benefit
          </div>
          <div class="h-[84px] border-t border-black flex">
            <div
              class="h-full w-[139px] border-r border-black text-center flex items-center pl-[10px]"
            >
              Pension Share
            </div>
            <div
              class="h-full w-[139px] border-r border-black flex text-center items-center px-4 pl-[10px]"
            >
              ER PF Share
            </div>
            <div class="h-full w-[151px] flex justify-center items-center pl-[10px]">
              EE Share
            </div>
          </div>
        </div>
        <div class="w-[146px] border border-black -ml-px text-center flex pt-[15px] pl-[10px]">
          Posting Location of the member
        </div>
      </div>
				<div class="border-t table-content">`

		const temp = res.map(item => {
			return `<div class="flex flex-nowrap text-[20px] -mt-px" style="height: ${
				+item.rowHigh + 2
			}px">
				<div class="w-[96px] flex justify-center items-center border border-black h-full -mt-px">
					${item.siNo || ''}
				</div>
				<div
					class="w-[159px] flex justify-center items-center border border-black h-full -mt-px -ml-px"
				>
					${item.uan || ''}
				</div>
				<div class="h-full w-[165px] border leading-[20px] border-black flex items-center px-[12px] -mt-px -ml-px">
					${item.ecr || ''}
				</div>
				<div class="h-full w-[140px] border leading-[20px] border-black flex items-center px-[12px] -mt-px -ml-px">
					${item.uanrepository || ''}
				</div>
				<div class="h-full w-[112px] border border-black flex justify-end px-[12px] -mt-px -ml-px">
					${item.gross || ''}
				</div>
				<div class="h-full w-[112px] border border-black flex justify-end px-[12px] -mt-px -ml-px">
					${item.epf || ''}
				</div>
				<div class="h-full w-[112px] border border-black flex justify-end px-[12px] -mt-px -ml-px">
					${item.eps || ''}
				</div>
				<div class="h-full w-[112px] border border-black flex justify-end px-[12px] -mt-px -ml-px">
					${item.edli || ''}
				</div>
				<div
					class="h-full w-[140px] border border-black flex justify-end items-center px-[12px] -mt-px -ml-px"
				>
					${item.ee || ''}
				</div>
				<div
					class="h-full w-[140px] border border-black flex justify-end items-center px-[12px] -mt-px -ml-px"
				>
					${item.creps || ''}
				</div>
				<div
					class="h-full w-[140px] border border-black flex justify-end items-center px-[12px] -mt-px -ml-px"
				>
					${item.er || ''}
				</div>
				<div class="h-full w-[98px] border border-black flex justify-end px-[12px] -mt-px -ml-px">
					${item.ncpdays || ''}
				</div>
				<div
					class="h-full w-[140px] border border-black flex items-center justify-end px-[12px] -mt-px -ml-px"
				>
					${item.refunds || ''}
				</div>
				<div
					class="h-full w-[140px] border border-black flex items-center justify-end px-[12px] -mt-px -ml-px"
				>
					${item.pensionShare ? item.pensionShare : '-'}
				</div>
				<div
					class="h-full w-[140px] border border-black flex items-center justify-end px-[12px] -mt-px -ml-px"
				>
					${item.erpfshare ? item.erpfshare : '-'}
				</div>
				<div
					class="h-full w-[153px] border border-black flex items-center justify-end px-[12px] -mt-px -ml-px"
				>
					${item.eeshare ? item.eeshare : '-'}
				</div>
				<div
					class="h-full w-[146px] border border-black flex items-center justify-end px-[12px] -mt-px -ml-px"
				>
					${item.postingLocationOfTheMember ? item.postingLocationOfTheMember : 'N.A.'}
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

